package au.edu.monash.infotech.myroo.model;

import android.util.Log;

import au.edu.monash.infotech.myroo.Constance;
import au.edu.monash.infotech.myroo.presenter.IRROPresenter;
import au.edu.monash.infotech.myroo.view.WeatherInfo;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RROModel implements IRROModel {

    public static final String TAG = RROModel.class.getSimpleName();

    private IRROPresenter irroPresenter;
    private String result = "";

    public RROModel(IRROPresenter irroPresenter) {
        this.irroPresenter = irroPresenter;
    }

    @Override
    public void requset() {
        Completable observer = Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Constance.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WeatherService service = retrofit.create(WeatherService.class);
                Call<WeatherInfo> call = service.getWeather(Constance.XIAMEN_ID, Constance.KEY);
                result = call.execute().body().base;
                emitter.onComplete();
            }
        });
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
                irroPresenter.onSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());

            }
        });
    }
}
