package com.app.cellstudio.holidayplanner.interactor.viewmodel;

import com.app.cellstudio.holidayplanner.data.repository.ConfigRepository;
import com.app.cellstudio.holidayplanner.interactor.scheduler.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 12/09/2018.
 */

public abstract class BaseViewModel implements ViewModel {
    private static final String TAG = BaseViewModel.class.getSimpleName();

    private final BaseSchedulerProvider scheduler;
    protected CompositeDisposable subscriptions;

    protected final ConfigRepository configRepository;

    private Input baseInput;
    private Output baseOutput;

    public BaseViewModel(ConfigRepository configRepository, BaseSchedulerProvider scheduler) {
        this.configRepository = configRepository;
        this.scheduler = scheduler;
        this.baseInput = new BaseInput();
        this.baseOutput = new BaseOutput();
    }

    protected BaseSchedulerProvider getScheduler() {
        return scheduler;
    }

    @Override
    public Input getInput() {
        return baseInput;
    }

    @Override
    public Output getOutput() {
        return baseOutput;
    }

    protected PublishSubject<String> getShowToastMessage() {
        return (PublishSubject<String>) getOutput().getShowToastMessage();
    }

    /**
     * Utility method to apply default schedulers automatically.
     * See dhttp://blog.danlew.net/2015/03/02/dont-break-the-chain/
     *
     * @param <T> type
     * @return transformer
     */
    protected <T> ObservableTransformer<T, T> applySchedulers(boolean showApiException) {
        return observable -> observable
                .subscribeOn(getScheduler().io())
                .observeOn(getScheduler().ui())
                .doOnError(Throwable::printStackTrace);
    }

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return applySchedulers(true);
    }

    protected void showApiException(Throwable throwable, String message) {
        getShowToastMessage().onNext(message);
    }

    public static class BaseOutput implements Output {
        protected PublishSubject<String> showToastMessage = PublishSubject.create();

        @Override
        public Observable<String> getShowToastMessage() {
            return showToastMessage;
        }
    }

    public class BaseInput implements Input {

        @Override
        public void onCreateView() {
            subscriptions = new CompositeDisposable();
        }

        @Override
        public void onAttachView() {
            if (subscriptions == null || subscriptions.isDisposed())
                subscriptions = new CompositeDisposable();
        }

        @Override
        public void onDetachView() {
            if (subscriptions != null)
                subscriptions.dispose();
        }
    }
}
