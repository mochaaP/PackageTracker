package info.papdt.express.helper.ui.fragment.settings;

import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceFragment;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.view.View;

import info.papdt.express.helper.R;
import info.papdt.express.helper.ui.SettingsActivity;

public abstract class AbsPrefFragment extends PreferenceFragment {

	public SettingsActivity getParentActivity() {
		return (SettingsActivity) getActivity();
	}

	public Snackbar makeSnackbar(String message, int duration) {
		return getParentActivity().makeSnackbar(message, duration);
	}

	public void makeRestartTips() {
		makeSnackbar(getString(R.string.toast_need_restart), Snackbar.LENGTH_SHORT)
				.setAction(R.string.toast_need_restart_action, new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent i = getParentActivity()
								.getBaseContext()
								.getPackageManager()
								.getLaunchIntentForPackage(getParentActivity().getPackageName());
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						getParentActivity().startActivity(i);
						getParentActivity().finish();
					}
				});
	}

	public void openWebsite(String url) {
		CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
		builder.setToolbarColor(getActivity().getResources().getColor(R.color.pink_500));
		builder.build().launchUrl(getActivity(), Uri.parse(url));
	}

}
