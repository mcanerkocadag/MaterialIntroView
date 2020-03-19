package co.mobiwise.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import co.mobiwise.materialintro.animation.MaterialIntroListener;
import co.mobiwise.materialintro.prefs.PreferencesManager;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;
import co.mobiwise.sample.R;

/**
 * Created by mertsimsek on 31/01/16.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    private static final String INTRO_CARD = "material_intro";

    private CardView cardView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        cardView = (CardView) view.findViewById(R.id.my_card);
        button = (Button) view.findViewById(R.id.button_reset_all);
        button.setOnClickListener(this);

        //Show intro
        showIntro(cardView, INTRO_CARD, "This is card! Hello There. You can set this text!");

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.button_reset_all)
            new PreferencesManager(getActivity().getApplicationContext()).resetAll();
    }

    private void showIntro(View view, String usageId, String text){
        new MaterialIntroView.Builder(getActivity())
                .enableDotAnimation(true)
                //.enableIcon(false)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(200)
                .enableFadeAnimation(true)
                .performClick(true)
                .setInfoText(text)
                .setTarget(view)
                .setShape(ShapeType.RECTANGLE)
                .setUsageId(usageId) //THIS SHOULD BE UNIQUE ID
                .setListener(new MaterialIntroListener() {
                    @Override
                    public void onUserClicked(String materialIntroViewId) {

                    }

                    @Override
                    public void onSkipClicked() {
                        Toast.makeText(getActivity(),"onSkipClicked()",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
}
