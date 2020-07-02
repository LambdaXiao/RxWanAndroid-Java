package app.android.rxwanandroidjava.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 描述：全局共享的ViewModel
 */
public class GlobalViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public MutableLiveData<String> getText() {
        if (text == null) {
            text = new MutableLiveData<String>();
        }
        return text;
    }
}
