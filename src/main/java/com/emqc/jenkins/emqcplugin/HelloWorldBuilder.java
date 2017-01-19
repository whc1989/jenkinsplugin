package com.emqc.jenkins.emqcplugin;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

	// private final String name;

	private final String iosTaskName;
	private final String iosPath;
	private boolean iosChecked = false;
	private final String androidTaskName;
	private final String androidPath;
	private boolean androidChecked = false;
	private final String androidStressTaskName;
	private final String androidStressPath;
	private final String androidStressTime;
	private boolean androidStressChecked = false;
	// private final String androidTaskName;

	public boolean selectediphone6;
	public boolean selectediphone6plus;
	public boolean selectedhuaweimate8;
	public boolean selectedhtce9pw;
	public boolean selectedoppor9;
	public boolean selectedhonour8;
	public boolean selectedvivox7;
	public boolean selectedmeizumx6;
	public boolean selectedmi5;

	public boolean selectedStresshuaweimate8;
	public boolean selectedStresshtce9pw;
	public boolean selectedStressoppor9;
	public boolean selectedStresshonour8;
	public boolean selectedStressvivox7;
	public boolean selectedStressmeizumx6;
	public boolean selectedStressmi5;

	// Fields in config.jelly must match the parameter names in the
	// "DataBoundConstructor"
	@DataBoundConstructor
	public HelloWorldBuilder(EnableTextBlock ios, EnableTextBlock2 android, EnableTextBlock3 androidStress) {
		// this.name = name;
		if (ios != null) {
			this.iosTaskName = ios.iosTaskName;
			this.iosPath = ios.iosPath;
			this.selectediphone6 = ios.selectediphone6;
			this.selectediphone6plus = ios.selectediphone6plus;
			this.iosChecked = true;
		} else {
			this.iosTaskName = null;
			this.iosPath = null;
			this.selectediphone6 = false;
			this.selectediphone6plus = false;

		}
		if (android != null) {
			this.androidTaskName = android.androidTaskName;
			this.androidPath = android.androidPath;
			this.selectedhuaweimate8 = android.selectedhuaweimate8;
			this.selectedhtce9pw = android.selectedhtce9pw;
			this.selectedoppor9 = android.selectedoppor9;
			this.selectedhonour8 = android.selectedhonour8;
			this.selectedvivox7 = android.selectedvivox7;
			this.selectedmeizumx6 = android.selectedmeizumx6;
			this.selectedmi5 = android.selectedmi5;
			this.androidChecked = true;
		} else {
			this.androidTaskName = null;
			this.androidPath = null;
			this.selectedhuaweimate8 = false;
			this.selectedhtce9pw = false;
			this.selectedoppor9 = false;
			this.selectedhonour8 = false;
			this.selectedvivox7 = false;
			this.selectedmeizumx6 = false;
			this.selectedmi5 = false;

		}
		if (androidStress != null) {
			this.androidStressTaskName = androidStress.androidStressTaskName;
			this.androidStressPath = androidStress.androidStressPath;
			this.androidStressTime = androidStress.androidStressTime;
			this.selectedStresshuaweimate8 = androidStress.selectedStresshuaweimate8;
			this.selectedStresshtce9pw = androidStress.selectedStresshtce9pw;
			this.selectedStressoppor9 = androidStress.selectedStressoppor9;
			this.selectedStresshonour8 = androidStress.selectedStresshonour8;
			this.selectedStressvivox7 = androidStress.selectedStressvivox7;
			this.selectedStressmeizumx6 = androidStress.selectedStressmeizumx6;
			this.selectedStressmi5 = androidStress.selectedStressmi5;

			this.androidStressChecked = true;
		} else {
			this.androidStressTaskName = null;
			this.androidStressPath = null;
			this.androidStressTime = null;
			this.selectedStresshuaweimate8 = false;
			this.selectedStresshtce9pw = false;
			this.selectedStressoppor9 = false;
			this.selectedStresshonour8 = false;
			this.selectedStressvivox7 = false;
			this.selectedStressmeizumx6 = false;
			this.selectedStressmi5 = false;

		}
	}

	public static class EnableTextBlock {
		private String iosTaskName;
		private String iosPath;
		public boolean selectediphone6;
		public boolean selectediphone6plus;

		@DataBoundConstructor
		public EnableTextBlock(String iosTaskName, String iosPath, boolean selectediphone6,
				boolean selectediphone6plus) {
			this.iosTaskName = iosTaskName;
			this.iosPath = iosPath;
			this.selectediphone6 = selectediphone6;
			this.selectediphone6plus = selectediphone6plus;
		}
	}

	public static class EnableTextBlock2 {
		private String androidTaskName;
		private String androidPath;
		public boolean selectedhuaweimate8;
		public boolean selectedhtce9pw;
		public boolean selectedoppor9;
		public boolean selectedhonour8;
		public boolean selectedvivox7;
		public boolean selectedmeizumx6;
		public boolean selectedmi5;

		@DataBoundConstructor
		public EnableTextBlock2(String androidTaskName, String androidPath, boolean selectedmeizumx6,
				boolean selectedmi5, boolean selectedhuaweimate8, boolean selectedhtce9pw, boolean selectedoppor9,
				boolean selectedhonour8, boolean selectedvivox7) {
			this.androidTaskName = androidTaskName;
			this.androidPath = androidPath;
			this.selectedhuaweimate8 = selectedhuaweimate8;
			this.selectedhtce9pw = selectedhtce9pw;
			this.selectedoppor9 = selectedoppor9;
			this.selectedhonour8 = selectedhonour8;
			this.selectedvivox7 = selectedvivox7;
			this.selectedmeizumx6 = selectedmeizumx6;
			this.selectedmi5 = selectedmi5;
		}
	}

	public static class EnableTextBlock3 {
		private String androidStressTaskName;
		private String androidStressPath;
		private String androidStressTime;
		public boolean selectedStresshuaweimate8;
		public boolean selectedStresshtce9pw;
		public boolean selectedStressoppor9;
		public boolean selectedStresshonour8;
		public boolean selectedStressvivox7;
		public boolean selectedStressmeizumx6;
		public boolean selectedStressmi5;

		@DataBoundConstructor
		public EnableTextBlock3(String androidStressTaskName, String androidStressPath, String androidStressTime,
				boolean selectedStressmeizumx6, boolean selectedStressmi5, boolean selectedStresshuaweimate8,
				boolean selectedStresshtce9pw, boolean selectedStressoppor9, boolean selectedStresshonour8,
				boolean selectedStressvivox7) {
			this.androidStressTaskName = androidStressTaskName;
			this.androidStressPath = androidStressPath;
			this.androidStressTime = androidStressTime;
			this.selectedStresshuaweimate8 = selectedStresshuaweimate8;
			this.selectedStresshtce9pw = selectedStresshtce9pw;
			this.selectedStressoppor9 = selectedStressoppor9;
			this.selectedStresshonour8 = selectedStresshonour8;
			this.selectedStressvivox7 = selectedStressvivox7;
			this.selectedStressmeizumx6 = selectedStressmeizumx6;
			this.selectedStressmi5 = selectedStressmi5;
		}
	}

	/**
	 * We'll use this from the {@code config.jelly}.
	 */
	// public String getName() {
	// return name;
	// }

	public String getIosTaskName() {
		return iosTaskName;
	}

	public String getAndroidStressTime() {
		return androidStressTime;
	}

	public boolean isSelectedStresshuaweimate8() {
		return selectedStresshuaweimate8;
	}

	public boolean isSelectedStresshtce9pw() {
		return selectedStresshtce9pw;
	}

	public boolean isSelectedStressoppor9() {
		return selectedStressoppor9;
	}

	public boolean isSelectedStresshonour8() {
		return selectedStresshonour8;
	}

	public boolean isSelectedStressvivox7() {
		return selectedStressvivox7;
	}

	public boolean isSelectedStressmeizumx6() {
		return selectedStressmeizumx6;
	}

	public boolean isSelectedStressmi5() {
		return selectedStressmi5;
	}

	public boolean isSelectedhuaweimate8() {
		return selectedhuaweimate8;
	}

	public boolean isSelectedhtce9pw() {
		return selectedhtce9pw;
	}

	public boolean isSelectedoppor9() {
		return selectedoppor9;
	}

	public boolean isSelectedhonour8() {
		return selectedhonour8;
	}

	public boolean isSelectedvivox7() {
		return selectedvivox7;
	}

	public boolean isSelectedmeizumx6() {
		return selectedmeizumx6;
	}

	public boolean isSelectedmi5() {
		return selectedmi5;
	}

	public boolean isSelectediphone6() {
		return selectediphone6;
	}

	public boolean isSelectediphone6plus() {
		return selectediphone6plus;
	}

	public boolean getIosChecked() {
		return iosChecked;
	}

	public String getAndroidTaskName() {
		return androidTaskName;
	}

	public boolean getAndroidChecked() {
		return androidChecked;
	}

	public String getAndroidStressTaskName() {
		return androidStressTaskName;
	}

	public String getAndroidStressPath() {
		return androidStressPath;
	}

	public boolean getAndroidStressChecked() {
		return androidStressChecked;
	}

	public String getIosPath() {
		return iosPath;
	}

	public String getAndroidPath() {
		return androidPath;
	}

	@Override
	public void perform(Run<?, ?> build, FilePath workspace, Launcher launcher, TaskListener listener) {
		// This is where you 'build' the project.
		// Since this is a dummy, we just say 'hello world' and call that a
		// build.

		// This also shows how you can consult the global configuration of the
		// builder
		// if (getDescriptor().getUseFrench())
		// listener.getLogger().println("Bonjour, " + name + "!");
		// else
		// listener.getLogger().println("Hello, " + name + "!");
		listener.getLogger().println("rtx, " + getDescriptor().getRtx() + "!");
		listener.getLogger().println("password, " + getDescriptor().getPassword() + "!");

		listener.getLogger().println("iosTaskName, " + iosTaskName + "!");
		listener.getLogger().println("androidTaskName, " + androidTaskName + "!");
		listener.getLogger().println("androidStressTaskName, " + androidStressTaskName + "!");
		listener.getLogger().println("iosPath, " + iosPath + "!");
		listener.getLogger().println("androidPath, " + androidPath + "!");
		listener.getLogger().println("androidStressPath, " + androidStressPath + "!");
		listener.getLogger().println("iosChecked, " + iosChecked + "!");
		listener.getLogger().println("androidChecked, " + androidChecked + "!");
		listener.getLogger().println("androidStressChecked, " + androidStressChecked + "!");
		if (iosChecked) {
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			String iosDevice = "";
			if (selectediphone6 == true) {
				if (iosDevice.equals("")) {
					iosDevice += "c6447a7b2e7c9828e998b1c56f2d3a962db4cf52";
				} else {
					iosDevice += ",c6447a7b2e7c9828e998b1c56f2d3a962db4cf52";
				}
			}
			if (selectediphone6plus == true) {
				if (iosDevice.equals("")) {
					iosDevice += "3f735327d5767dcd1633f004737936713204992d";
				} else {
					iosDevice += ",3f735327d5767dcd1633f004737936713204992d";
				}
			}
			listener.getLogger().println("iosDevice, " + iosDevice + "!");
			// JSONObject
			// sr=HttpRequest.sendPost("http://localhost:8080/emqc-platform/login",
			// "userName="+getDescriptor().getRtx()+"&passWord="+getDescriptor().getPassword());
			org.json.JSONObject sr = HttpRequest.sendPost("http://172.16.96.146:8081/emqc-platform/login",
					"userName=" + getDescriptor().getRtx() + "&passWord=" + getDescriptor().getPassword());
			listener.getLogger().println("uuId, " + sr.get("uuId").toString() + "!");
			org.json.JSONObject sr2 = HttpPostUploadUtil.initClass(
					"http://172.16.96.146:8081/emqc-platform/ios_uploadapk.action", sr.get("uuId").toString(), iosPath);
			listener.getLogger().println("devices, " + sr2.get("devices") + "!");
			org.json.JSONObject sr3 = HttpRequest.sendPost("http://172.16.96.146:8081/emqc-platform/ios_taskrun.action",
					"name=" + iosTaskName + "&testPhones=" + iosDevice + "&testPackage="
							+ sr2.get("packageName").toString() + "&appPath=" + sr2.get("appPath").toString()
							+ "&appName=" + sr2.get("appName") + "&appVersion=" + sr2.get("appVersion").toString()
							+ "&uuid=" + sr.get("uuId").toString());
			listener.getLogger().println("success, " + sr3.get("success").toString() + "!");
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		}
		if (androidChecked) {
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			String androidDevice = "";
			if (selectedhuaweimate8 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "5LM0216304000377";
				} else {
					androidDevice += ",5LM0216304000377";
				}
			}
			if (selectedhtce9pw == true) {
				if (androidDevice.equals("")) {
					androidDevice += "HC614YHE0116";
				} else {
					androidDevice += ",HC614YHE0116";
				}
			}
			if (selectedoppor9 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "UO8TG6M799999999";
				} else {
					androidDevice += ",UO8TG6M799999999";
				}
			}
			if (selectedhonour8 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "WTKDU16905010628";
				} else {
					androidDevice += ",WTKDU16905010628";
				}
			}
			if (selectedvivox7 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "25ccf417";
				} else {
					androidDevice += ",25ccf417";
				}
			}
			if (selectedmeizumx6 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "M95QACPCLX7C2";
				} else {
					androidDevice += ",M95QACPCLX7C2";
				}
			}
			if (selectedmi5 == true) {
				if (androidDevice.equals("")) {
					androidDevice += "aeb0e21a";
				} else {
					androidDevice += ",aeb0e21a";
				}
			}
			listener.getLogger().println("androidDevice, " + androidDevice + "!");
			org.json.JSONObject sr = HttpRequest.sendPost("http://172.16.96.146:8081/emqc-platform/login",
					"userName=" + getDescriptor().getRtx() + "&passWord=" + getDescriptor().getPassword());
			listener.getLogger().println("uuId, " + sr.get("uuId").toString() + "!");
			org.json.JSONObject sr2 = HttpPostUploadUtil.initClass(
					"http://172.16.96.146:8081/emqc-platform/android_uploadapk.action", sr.get("uuId").toString(),
					androidPath);
			listener.getLogger().println("devices, " + sr2.get("devices") + "!");
			org.json.JSONObject sr3 = HttpRequest.sendPost("http://172.16.96.146:8081/emqc-platform/android_taskrun.action",
					"name=" + androidTaskName + "&testPhones=" + androidDevice + "&testPackage="
							+ sr2.get("packageName").toString() + "&appPath=" + sr2.get("appPath").toString()
							+ "&appName=" + sr2.get("appName") + "&launchActivity=" + sr2.get("launchActivity")
							+ "&appVersion=" + sr2.get("appVersion").toString() + "&uuid=" + sr.get("uuId").toString());
			listener.getLogger().println("success, " + sr3.get("success").toString() + "!");
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		}
		if (androidStressChecked) {
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			String androidStressDevice = "";
			if (selectedStresshuaweimate8 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "5LM0216304000377";
				} else {
					androidStressDevice += ",5LM0216304000377";
				}
			}
			if (selectedStresshtce9pw == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "HC614YHE0116";
				} else {
					androidStressDevice += ",HC614YHE0116";
				}
			}
			if (selectedStressoppor9 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "UO8TG6M799999999";
				} else {
					androidStressDevice += ",UO8TG6M799999999";
				}
			}
			if (selectedStresshonour8 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "WTKDU16905010628";
				} else {
					androidStressDevice += ",WTKDU16905010628";
				}
			}
			if (selectedStressvivox7 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "25ccf417";
				} else {
					androidStressDevice += ",25ccf417";
				}
			}
			if (selectedStressmeizumx6 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "M95QACPCLX7C2";
				} else {
					androidStressDevice += ",M95QACPCLX7C2";
				}
			}
			if (selectedStressmi5 == true) {
				if (androidStressDevice.equals("")) {
					androidStressDevice += "aeb0e21a";
				} else {
					androidStressDevice += ",aeb0e21a";
				}
			}
			listener.getLogger().println("androidStressDevice, " + androidStressDevice + "!");
			org.json.JSONObject sr = HttpRequest.sendPost("http://172.16.96.146:8081/emqc-platform/login",
					"userName=" + getDescriptor().getRtx() + "&passWord=" + getDescriptor().getPassword());
			listener.getLogger().println("uuId, " + sr.get("uuId").toString() + "!");
			org.json.JSONObject sr2 = HttpPostUploadUtil.initClass(
					"http://172.16.96.146:8081/emqc-platform/android_uploadapk.action", sr.get("uuId").toString(),
					androidStressPath);
			listener.getLogger().println("devices, " + sr2.get("devices") + "!");
			org.json.JSONObject sr3 = HttpRequest.sendPost(
					"http://172.16.96.146:8081/emqc-platform/android_stress_taskrun.action",
					"name=" + androidStressTaskName + "&testPhones=" + androidStressDevice + "&testPackage="
							+ sr2.get("packageName").toString() + "&appPath=" + sr2.get("appPath").toString()
							+ "&appName=" + sr2.get("appName") + "&launchActivity=" + sr2.get("launchActivity")
							+ "&monkeyScript=" + androidStressTime + "&appVersion=" + sr2.get("appVersion").toString()
							+ "&uuid=" + sr.get("uuId").toString());
			listener.getLogger().println("success, " + sr3.get("success").toString() + "!");
			listener.getLogger().println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		}
	}

	// Overridden for better type safety.
	// If your plugin doesn't really define any property on Descriptor,
	// you don't have to do this.
	@Override
	public DescriptorImpl getDescriptor() {
		return (DescriptorImpl) super.getDescriptor();
	}

	@Extension // This indicates to Jenkins that this is an implementation of an
				// extension point.
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

		// private boolean useFrench;
		private String rtx;
		private String password;

		/**
		 * In order to load the persisted global configuration, you have to call
		 * load() in the constructor.
		 */
		public DescriptorImpl() {
			load();
		}

		// public FormValidation doCheckName(@QueryParameter String value)
		// throws IOException, ServletException {
		// if (value.length() == 0)
		// return FormValidation.error("Please set a name");
		// if (value.length() < 4)
		// return FormValidation.warning("Isn't the name too short?");
		// return FormValidation.ok();
		// }

		public boolean isApplicable(Class<? extends AbstractProject> aClass) {
			// Indicates that this builder can be used with all kinds of project
			// types
			return true;
		}

		/**
		 * This human readable name is used in the configuration screen.
		 */
		public String getDisplayName() {
			return "EMQC";
		}

		@Override
		public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
			// To persist global configuration information,
			// set that to properties and call save().
			// useFrench = formData.getBoolean("useFrench");
			rtx = formData.getString("rtx");
			password = formData.getString("password");
			// ^Can also use req.bindJSON(this, formData);
			// (easier when there are many fields; need set* methods for this,
			// like setUseFrench)
			save();
			return super.configure(req, formData);
		}

		/**
		 * This method returns true if the global configuration says we should
		 * speak French.
		 *
		 * The method name is bit awkward because global.jelly calls this method
		 * to determine the initial state of the checkbox by the naming
		 * convention.
		 */
		// public boolean getUseFrench() {
		// return useFrench;
		// }

		public String getRtx() {
			return rtx;
		}

		public String getPassword() {
			return password;
		}

	}
}
