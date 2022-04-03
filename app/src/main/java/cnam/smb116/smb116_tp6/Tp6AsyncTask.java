package cnam.smb116.smb116_tp6;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import androidx.lifecycle.ViewModelProvider;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.ref.WeakReference;

public class Tp6AsyncTask extends AsyncTask<Void, String, Void> {

    private static final String TAG = Tp6AsyncTask.class.getSimpleName();
    WeakReference<ButtonFragment> fragmentWeakReference;
    WeakReference<ButtonViewModel> buttonViewModelWeakReference;
    WeakReference<Integer> state;
    int i;

    public Tp6AsyncTask(ButtonFragment fragment, Integer state){
        this.fragmentWeakReference = new WeakReference<>(fragment);
        this.buttonViewModelWeakReference = new WeakReference<>(new ViewModelProvider(fragmentWeakReference.get().requireActivity()).get(ButtonViewModel.class));
        this.state = new WeakReference<>(state);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.i(TAG, "Do in background");

        try {
            InputStreamReader isr = new InputStreamReader(fragmentWeakReference.get().requireActivity().getAssets().open("canton2015.csv"),"ISO_8859_1"); // StandardCharsets.ISO_8859_1);
            LineNumberReader lnr = new LineNumberReader(isr);

             i = 0;

             /* Question 2 */
            if (state.get() != 0){
                lnr.setLineNumber(state.get());
                i = state.get();
            }

            String s;
            /* Question 2 */
            while ((s = lnr.readLine()) != null
                    && !isCancelled()){

                publishProgress(s);          // à chaque ligne lue depuis le fichier, la liste est réactualisée
                SystemClock.sleep(2);       // délai en fonction des performances du périphérique Android

                Log.i(TAG,String.valueOf(i));

                i++;
            }
            lnr.close();
            isr.close();
        }catch(Exception e){
//            if(I)Log.i(TAG,"Exception: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        buttonViewModelWeakReference.get().addStringToList(values[0]);
        buttonViewModelWeakReference.get().setLoadProgress(i);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "Canceled");
    }
}
