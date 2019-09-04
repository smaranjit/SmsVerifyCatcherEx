# SmsVerifyCatcherEx
#### SmsVerifyCatcherEx is the modified and extended version of [SmsVerifyCatcher](https://github.com/stfalcon-studio/SmsVerifyCatcher) library,

for intercepting SMS and getting OTP from the SMS

## Add to your project

`implementation 'io.github.smaranjit:smsverifycatcherex:0.1.1'`

## Permissions

### add these permissions to `AndroidManifest.xml`

```xml
  <uses-permission android:name="android.permission.RECEIVE_SMS" />
  <uses-permission android:name="android.permission.READ_SMS" />
  ```
## Initialization

```java
    smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
        @Override
        public void onSmsCatch(String message) {
            String code = parseCode(message);//Parse verification code
            etCode.setText(code);//set code in edit text
            //then you can send verification code to server
        }
    });
```
## Starting
`smsVerifyCatcher.onStart();`

## Stopping

`smsVerifyCatcher.onStop();`

## Filter sender phone no.
`smsVerifyCatcher.setPhoneNumberFilter("777");`
## Filter sender Id.
`smsVerifyCatcher.setPhoneNumberFilterEx("senderId");`