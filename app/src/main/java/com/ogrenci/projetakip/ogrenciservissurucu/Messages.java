package com.ogrenci.projetakip.ogrenciservissurucu;

import android.content.Context;
import android.widget.Toast;

public class Messages {
    public static void enterDealerCode(Context context) {
        Business.giveToast(context, "Bayi Kodunu Girin!", Toast.LENGTH_LONG);
    }
    public static void enterUserName(Context context) {
        Business.giveToast(context, "Kullanıcı Adını Girin!", Toast.LENGTH_LONG);
    }

    public static void enterPassword(Context context) {
        Business.giveToast(context, "Şifrenizi Girin!", Toast.LENGTH_LONG);
    }

    public static void incorrectInformation(Context context) {
        Business.giveToast(context, "Hatalı Bilgiler Girdiniz!", Toast.LENGTH_LONG);
    }
}
