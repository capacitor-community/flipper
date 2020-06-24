package com.getcapacitor.community.flipper;

import android.content.Context;
import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin;
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin;
import com.facebook.flipper.plugins.databases.impl.SqliteDatabaseDriver;
import com.facebook.flipper.plugins.databases.impl.SqliteDatabaseProvider;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin;
import com.facebook.soloader.SoLoader;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@NativePlugin
public class Flipper extends Plugin {
  private static final String TAG = "Flipper";

  private Context applicationContext;

  private boolean isEnabled = true;
  private boolean networkEnabled = false;
  private boolean crashReportEnabled = false;
  private boolean layoutInspectorEnabled = false;

  private boolean databaseEnabled = false;
  private String databasePath;

  @Override
  public void load() {
    super.load();

    if (this.applicationContext == null) {
      this.applicationContext =
        this.bridge.getActivity().getApplicationContext();
    }
  }

  @PluginMethod
  public void initialize(PluginCall call) {
    if (call.hasOption("network")) {
      this.networkEnabled = call.getBoolean("network", false);
    }

    if (call.hasOption("crash_report")) {
      this.crashReportEnabled = call.getBoolean("crash_report", false);
    }

    if (call.hasOption("layout_inspector")) {
      this.layoutInspectorEnabled = call.getBoolean("layout_inspector", false);
    }

    if (call.hasOption("enabled")) {
      this.isEnabled = call.getBoolean("enabled", true);
    }

    if (call.hasOption("database")) {
      this.databaseEnabled = call.getBoolean("database", false);

      if (call.hasOption("database_path")) {
        this.databasePath = call.getString("database_path");
      }
    }

    if (this.isEnabled) {
      SoLoader.init(this.applicationContext, false);

      final FlipperClient client = AndroidFlipperClient.getInstance(
        this.applicationContext
      );

      if (this.layoutInspectorEnabled) {
        client.addPlugin(
          new InspectorFlipperPlugin(
            this.applicationContext,
            DescriptorMapping.withDefaults()
          )
        );
      }

      if (this.networkEnabled) {
        NetworkFlipperPlugin networkFlipperPlugin = new NetworkFlipperPlugin();
        client.addPlugin(networkFlipperPlugin);
      }

      if (this.crashReportEnabled) {
        client.addPlugin(CrashReporterPlugin.getInstance());
      }

      if (this.databaseEnabled) {
        if (this.databasePath != null && !this.databasePath.isEmpty()) {
          client.addPlugin(
            new DatabasesFlipperPlugin(
              new SqliteDatabaseDriver(
                this.applicationContext,
                new SqliteDatabaseProvider() {

                  @Override
                  public List<File> getDatabaseFiles() {
                    List<File> databaseFiles = new ArrayList<>();
                    for (String databaseName : applicationContext.databaseList()) {
                      databaseFiles.add(
                        applicationContext.getDatabasePath(databaseName)
                      );
                    }
                    databaseFiles.add(new File(databasePath));
                    return databaseFiles;
                  }
                }
              )
            )
          );
        } else {
          client.addPlugin(new DatabasesFlipperPlugin(this.applicationContext));
        }
      }

      client.start();

      call.success();
    }
  }

  @PluginMethod
  public void emulateCrash(PluginCall call) {
    if (this.crashReportEnabled && call.hasOption("message")) {
      String message = call.getString("message");

      CrashReporterPlugin
        .getInstance()
        .sendExceptionMessage(Thread.currentThread(), new Throwable(message));

      call.success();
    }
  }
}
