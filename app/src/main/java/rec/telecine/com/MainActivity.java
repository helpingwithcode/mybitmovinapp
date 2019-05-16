/*
 * Bitmovin Player Android SDK
 * Copyright (C) 2017, Bitmovin GmbH, All Rights Reserved
 *
 * This source code and its use and distribution, is subject to the terms
 * and conditions of the applicable license agreement.
 */

package rec.telecine.com;

import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bitmovin.player.config.PlayerConfiguration;
import com.bitmovin.player.config.StyleConfiguration;
import com.bitmovin.player.config.media.SourceConfiguration;
import com.bitmovin.player.ui.FullscreenHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TELECINE_VIDEO_URL = "https://ctsr525679amd-theplatform.akamaized.net/video/Telecine_-_Production/344/733/TVODR-017410_58790469901_mp4_video_640x360_408000_primary_audio_eng_1_1556900850314.mpd?hdnts=st=1556915014~exp=1556915344~acl=/Telecine_-_Production/344/733/*~hmac=a594a75d4f8af1a32f7ba71c1da4c47576fad4e8be8bef461f57c8e4721ae738";
    private PlayerUI playerUi;
    private FullscreenHandler fullscreenHandler;
    @BindView(R.id.activity_main)
    LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // Create new StyleConfiguration
        StyleConfiguration styleConfiguration = new StyleConfiguration();
        // Disable UI
        styleConfiguration.setUiEnabled(false);

        // Create a new source configuration
        SourceConfiguration sourceConfiguration = new SourceConfiguration();
        // Add a new source item
        sourceConfiguration.addSourceItem(TELECINE_VIDEO_URL);

        // Creating a new PlayerConfiguration
        PlayerConfiguration playerConfiguration = new PlayerConfiguration();
        // Assign created StyleConfiguration to the PlayerConfiguration
        playerConfiguration.setStyleConfiguration(styleConfiguration);
        // Assign created SourceConfiguration to the PlayerConfiguration
        playerConfiguration.setSourceConfiguration(sourceConfiguration);

        this.playerUi = new PlayerUI(this, playerConfiguration);
        this.fullscreenHandler = new CustomFullscreenHandler(this, playerUi);

        // Set the FullscreenHandler of the PlayerUI
        this.playerUi.setFullscreenHandler(fullscreenHandler);


        this.playerUi.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(this.playerUi);
    }

    @Override
    protected void onStart() {
        this.playerUi.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.playerUi.onResume();
    }

    @Override
    protected void onPause() {
        this.playerUi.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        this.playerUi.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        this.playerUi.onDestroy();
        super.onDestroy();
    }
}
