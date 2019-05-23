package au.edu.monash.infotech.myroo.presenter;

public interface IRROPresenter {
    void request();
    void onSuccess(String msg);
    void onFail();
}
