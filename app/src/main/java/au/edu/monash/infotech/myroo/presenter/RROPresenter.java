package au.edu.monash.infotech.myroo.presenter;

import au.edu.monash.infotech.myroo.model.RROModel;
import au.edu.monash.infotech.myroo.view.IRROView;

public class RROPresenter implements IRROPresenter {

    private IRROView irroView;
    private RROModel rroModel;

    public RROPresenter(IRROView irroView) {
        this.irroView = irroView;
        this.rroModel = new RROModel(this);
    }

    @Override
    public void request() {
        rroModel.requset();
    }

    @Override
    public void onSuccess(String msg) {
        irroView.onSuccess(msg);
    }

    @Override
    public void onFail() {
        irroView.onFail();
    }
}
