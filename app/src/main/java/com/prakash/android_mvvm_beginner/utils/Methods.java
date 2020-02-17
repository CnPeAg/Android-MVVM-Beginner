package com.prakash.android_mvvm_beginner.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Preeti on 23-08-2017.
 */

public class Methods {

    static ProgressBar pb;
    static Dialog dialog;
    static TextView cur_val;

    public static String getName(String title, String name) {
        String str = "";
        if (title != null) {
            if (title.equalsIgnoreCase("1")) {
                str = "Dr. " + name;
            } else {
                str = name;
            }
        } else {
            str = name;
        }
        return str;
    }


    public static String getNameWithTitle(String title, String name) {
        String str = "";
        if (title != null) {
            if (title.equalsIgnoreCase("1")) {
                str = "Dr. " + name;
            } else if (title.equalsIgnoreCase("2")) {
                str = "Mr. " + name;
            } else if (title.equalsIgnoreCase("3")) {
                str = "Mrs. " + name;
            } else if (title.equalsIgnoreCase("4")) {
                str = "Ms. " + name;
            } else {
                str = name;
            }
        } else {
            str = name;
        }
        return str;
    }





    public static boolean validCellPhone(String number) {
        return Patterns.PHONE.matcher(number).matches();
    }

    public static boolean checkStringContainsValidUrl(String str) {
        String strarr[] = str.split(" ");
        for (int i = 0; i < strarr.length; i++) {
            System.out.print(strarr[i]);
            if (Patterns.WEB_URL.matcher(strarr[i]).matches()) {
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void downloadFile(String dwnload_file_path, Activity activity, String extention, String name) {
        new downloadDocument(dwnload_file_path, name, extention, activity).execute();
    }


    public static boolean validWebOrBlog(String str) {
        if (URLUtil.isValidUrl(str))
            return true;
        return false;


    }

    public static boolean validWebUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();

    }

    private static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }

    public static String getUrl(String s) {
        try {
            int i = 0;

            SpannableString spannableString = new SpannableString(s);
            Matcher urlMatcher = Patterns.WEB_URL.matcher(s);
            while (urlMatcher.find()) {
                String url = urlMatcher.group(i);
                int start = urlMatcher.start(i);
                int end = urlMatcher.end(i++);
                if (isValidUrl(url)) {
                    return url;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void openDocument(Context context, String typeOfDoc, String fileName) {

        File file = new File(Environment.getExternalStorageDirectory(),
                fileName + "." + typeOfDoc);
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/" + typeOfDoc);
        try {
            context.startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Downloaded", Toast.LENGTH_LONG).show();
        }
    }

    static void showError(final String err, final Context context) {
        Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
    }

    public static boolean isPastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return (date.before(calendar.getTime())) ? true : false;
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (phone.length() < 10 || phone.length() > 10) {
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String s) {
        if (s.length() < 6)
            return false;
        return true;
    }

    public static void hideSoftKeyboard(Activity activity) {
        if(activity!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View focusedView = activity.getCurrentFocus();
            if (focusedView != null) {
                inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
            //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(View view, Activity activity) {
        if(activity!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    public static String timeStamp(long strTime) {
        try {
            long now = System.currentTimeMillis();
            long dateTime = strTime * 1000L;
            CharSequence relaventTime = DateUtils.getRelativeTimeSpanString(
                    dateTime,
                    now,
                    DateUtils.SECOND_IN_MILLIS);
            return relaventTime.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String getCapsWord(String tagName) {
        String[] splits = tagName.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splits.length; i++) {
            String eachWord = splits[i];
            if (i > 0 && eachWord.length() > 0) {
                sb.append(" ");
            }
            String cap = eachWord.substring(0, 1).toUpperCase()
                    + eachWord.substring(1);
            sb.append(cap);
        }
        return sb.toString();
    }

    public static void openFile(Context context, Uri uri) {
        try {
            //Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            String fileName = uri.getLastPathSegment();
            if (fileName == null) {
                Toast.makeText(context, "File type not supported", Toast.LENGTH_SHORT).show();
                return;
            }
            if (fileName.contains(".doc") || fileName.contains(".docx")) {// Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (fileName.contains(".pdf")) {// PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (fileName.contains(".ppt") || fileName.contains(".pptx")) {// Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (fileName.contains(".xls") || fileName.contains(".xlsx")) {// Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (fileName.contains(".zip") || fileName.contains(".rar")) {// WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if (fileName.contains(".rtf")) {// RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if (fileName.contains(".wav") || fileName.contains(".mp3")) {// WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (fileName.contains(".gif")) {// GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if (fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".png")) {// JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (fileName.contains(".txt")) {// Text file
                intent.setDataAndType(uri, "text/plain");
            } else if (fileName.contains(".3gp") || fileName.contains(".mpg") || fileName.contains(".mpeg") ||
                    fileName.contains(".mpe") || fileName.contains(".mp4") || fileName.contains(".avi")) {// Video files
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }
            Intent target = Intent.createChooser(intent, "Open File");
            context.startActivity(target);
        } catch (Exception e) {
            Toast.makeText(context, "No application found which can open the file", Toast.LENGTH_SHORT).show();
        }
    }

    public static class downloadDocument extends AsyncTask<Void, Void, Void> {
        String dwnload_file_path;
        String name, extention;
        Activity activity;

        public downloadDocument(String dwnload_file_path, String name, String extention, Activity activity) {
            this.dwnload_file_path = dwnload_file_path;
            this.name = name;
            this.activity = activity;
            this.extention = extention;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                InputStream input = null;
                OutputStream output = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(dwnload_file_path);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    int fileLength = connection.getContentLength();

                    input = connection.getInputStream();
                    output = new FileOutputStream("/sdcard/" + name + "." + extention);

                    byte data[] = new byte[4096];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        // allow canceling with back button
                        if (isCancelled()) {
                            input.close();
                            return null;
                        }
                        total += count;
                        // publishing the progress....
                        if (fileLength > 0) // only if total length is known
                            //  publishProgress((int) (total * 100 / fileLength));
                            output.write(data, 0, count);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (output != null)
                            output.close();
                        if (input != null)
                            input.close();
                    } catch (IOException ignored) {
                    }

                    if (connection != null)
                        connection.disconnect();
                }

            } catch (final Exception e) {
                Toast.makeText(activity, "Error : Please check your internet connection ", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            openDocument(activity, extention, name);
            dialog.cancel();
        }
    }
}
