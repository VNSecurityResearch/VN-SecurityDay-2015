package corp.hptvietnam.antirepacking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by whitehatpanda on 11/16/15.
 */
public class SignatureChecker {

    private String expectedSig = "f0a5eed31e17f36a5031bbc7b77c8215";

    private Context mContext;

    SignatureChecker (Context context) {
        this.mContext = context;
    }

    public String getSignature() {
        PackageInfo pkInfo;
        String result;
        try {
            pkInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : pkInfo.signatures) {
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    result = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                    //Log.d("Package-Signature", result);
                    return getMD5(result);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean isModified() {
        String sig = getSignature();
        String expectedSig = getExpectedSig();
        Log.d("App-Sig", sig);
        return !sig.equalsIgnoreCase(expectedSig);
    }

    private String getMD5(String input) {
        MessageDigest m= null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(input.getBytes(), 0, input.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getExpectedSig() {
        return expectedSig;
    }
}
