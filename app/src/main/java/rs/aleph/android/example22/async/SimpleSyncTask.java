package rs.aleph.android.example22.async;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import rs.aleph.android.example22.tools.ReviewerTools;

import static rs.aleph.android.example22.tools.ReviewerTools.getConnectionType;

/**
 * Created by milossimic on 10/22/16.
 * AsyncTask klasa prima tri parametra prilikom specijalizacije
 * Korisnici sami definisu tip u zavisnosti od posla koji zele da obave.
 *
 * Prvi argument predstavlja ulazne parametre, ono so zelimo
 * da posaljemo zadatku. Recimo ime fajla koji zelimo da skinemo
 *
 * Drugi argument je indikator kako ce se meriti progres. Koliko je posla
 * zavrseno i koliko je opsla ostalo.
 *
 * Treci parametar je povratna vrednost, tj sta ce metoda doInBackground
 * vratiti kao poratnu vrednost metodi onPostExecute
 */
public class SimpleSyncTask extends AsyncTask<Integer, Void, Integer>{

    private Context context;
//    private Integer statusKonekcije;

    public SimpleSyncTask(Context context) {
        this.context = context;
    }

    /**
     * Metoda se poziva pre samog starta pozadinskog zadatka
     * Sve pripreme odraditi u ovoj metodi, ako ih ima.
     */
    @Override
    protected void onPreExecute() {
    }

    /**
     * Posao koji se odvija u pozadini, ne blokira glavnu nit aplikacije.
     * Sav posao koji dugo traje izvrsavati unutar ove metode.
     */
    @Override
    protected Integer doInBackground(Integer... params) {
        try {
//            statusKonekcije = params[0];
            //simulacija posla koji se obavlja u pozadini i traje duze vreme
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
    private void createNotification(String contentTitle, String contentText) {

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Build the notification using Notification.Builder
        Notification.Builder builder = new Notification.Builder(context)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setAutoCancel(true)
                .setContentTitle(contentTitle)
                .setContentText(contentText);


        //Show the notification
        mNotificationManager.notify(1, builder.build());
    }

    /**
     *
     * Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
     * Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.
     */

    @Override
    protected void onPostExecute(Integer type) {
        String text = ReviewerTools.getConnectionType(type);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

        //dodatni zadatak
        createNotification("Termin 22 dodatni zadatak", text);
    }
}
