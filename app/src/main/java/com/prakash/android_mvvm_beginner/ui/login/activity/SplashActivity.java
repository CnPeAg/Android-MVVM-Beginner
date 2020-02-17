package com.prakash.android_mvvm_beginner.ui.login.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.prakash.android_mvvm_beginner.R;
import com.prakash.android_mvvm_beginner.model.SingletonIntutionData;
import com.prakash.android_mvvm_beginner.ui.login.model.IntutionResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.model.VersionCheckResponceModel;
import com.prakash.android_mvvm_beginner.ui.login.viewModel.SplashViewModel;
import com.prakash.android_mvvm_beginner.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel splashViewModel;
    private String version;
    private Dialog dialogPopUp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //FirebaseApp.initializeApp(this);
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);

         PackageInfo pInfo = null;
        try {
            pInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            version = pInfo.versionName;
            // versionTV.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



        // Create the observer which updates the UI.
        final Observer<VersionCheckResponceModel> versionObserver = new Observer<VersionCheckResponceModel>() {
            @Override
            public void onChanged(@Nullable final VersionCheckResponceModel versionCheckResponceModel) {
                // Update the UI, in this case
                Double minVersion, latestVersion;
                int is_need_to_update = 0;
                if (versionCheckResponceModel != null) {
                    if (versionCheckResponceModel.getAndroidModel()!=null) {
                        if (versionCheckResponceModel.getAndroidModel().getIsNeedToUpdate()!=null) {
                            is_need_to_update = versionCheckResponceModel.getAndroidModel().getIsNeedToUpdate();
                        }
                        if (versionCheckResponceModel.getAndroidModel().getMinversion()!=null) {

                            minVersion = versionCheckResponceModel.getAndroidModel().getMinversion();
                            if (Double.compare(minVersion, Double.parseDouble(version)) > 0) {
                                if (dialogPopUp == null) {
                                    addDialog(is_need_to_update);
                                }
                            } else if (versionCheckResponceModel.getAndroidModel().getLatestversion()!=null) {
                                latestVersion = versionCheckResponceModel.getAndroidModel().getLatestversion();
                                if (Double.compare(latestVersion, Double.parseDouble(version)) > 0) {
                                    if (dialogPopUp == null) {
                                        addDialog(is_need_to_update);
                                    }
                                }

                            }

                        }
                    }
                }
            }
        };
        // Create the observer which updates the UI.
        final Observer<IntutionResponceModel> intutioObserver = new Observer<IntutionResponceModel>() {
            @Override
            public void onChanged(@Nullable final IntutionResponceModel intutionResponceModel) {
                // Update the UI, in this case
                if (intutionResponceModel != null) {
                    SingletonIntutionData.getInstance().setIntutionModel(intutionResponceModel);
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        splashViewModel.liveDataVersion.observe(this, versionObserver);
        splashViewModel.liveDataIntution.observe(this,intutioObserver);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    private void addDialog(int is_need_to_update) {

        dialogPopUp = new Dialog(SplashActivity.this);
        dialogPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPopUp.setCanceledOnTouchOutside(false);
        dialogPopUp.setContentView(R.layout.dialog_app_update);
        TextView skip = dialogPopUp.findViewById(R.id.skip);
        TextView update = dialogPopUp.findViewById(R.id.update);
        if (is_need_to_update > 0) {
            dialogPopUp.findViewById(R.id.skip).setVisibility(View.GONE);
        } else {
            dialogPopUp.findViewById(R.id.skip).setVisibility(View.VISIBLE);
        }
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SessionManager.getInstance(getApplication()).isUserLoggedIn()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                dialogPopUp.cancel();

                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.app.nirogstreet"));
                startActivity(intent);
            }
        });
        dialogPopUp.show();

    }

}
