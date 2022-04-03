package cnam.smb116.smb116_tp6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class ButtonViewModel extends ViewModel {

    private final MutableLiveData<List<String>> strList = new MutableLiveData<>();
    private final MutableLiveData<Integer> loadProgress = new MutableLiveData<>();

    public LiveData<List<String>> getstrList() {
        return strList;
    }

    public void addStringToList(String s){
        if (strList.getValue() == null){
            strList.setValue(new ArrayList<>());
        }else {
            List<String> list = strList.getValue();
            list.add(s);
            strList.setValue(list);
        }
    }

    public LiveData<Integer> getLoadProgress(){
        if (loadProgress.getValue() == null){
            loadProgress.setValue(0);
        }
        return loadProgress;
    }

    public void setLoadProgress(Integer i){
        loadProgress.setValue(i);
    }
}