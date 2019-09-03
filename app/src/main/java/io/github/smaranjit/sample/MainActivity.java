package io.github.smaranjit.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.EditText;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import io.github.smaranjit.smsverifycatcherex.OnSmsCatchListener;
import io.github.smaranjit.smsverifycatcherex.SmsVerifyCatcher;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    SmsVerifyCatcher mSmsVerifyCatcher;
    EditText otpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ask for required permission
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        //TODO:
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        //TODO:
                    }
                }).check();

        otpView = findViewById(R.id.editTextOtp);

        mSmsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {


            @Override
            public void onSmsCatch(String message) {
                String code = parseCode(message);//Parse verification code
                otpView.setText(code);
                //then you can send verification code to server
            }

            private String parseCode(String message) {
                String val = "";
                System.out.println(message);
                Pattern pattern = Pattern.compile("(\\d{6})");

                //   \d is for a digit
                //   {} is the number of digits here 4.

                Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    val = matcher.group(0);  // 4 digit number
                }
                System.out.println(val);
                return val;
            }
        });

        mSmsVerifyCatcher.setPhoneNumberFilterEx("xxxXX");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSmsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSmsVerifyCatcher.onStop();
    }

    /**
     * need for Android 6 real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mSmsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
