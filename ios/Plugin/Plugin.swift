import Foundation
import Capacitor
import FlipperKit

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitor.ionicframework.com/docs/plugins/ios
 */
@objc(Flipper)
public class Flipper: CAPPlugin {
    
    var isEnabled: Bool = true
    var networkEnabled: Bool = true
    var crashReportEnabled: Bool = false
    var layoutInspectorEnabled: Bool = false
    
    var databaseEnabled: Bool = false
    var databasePath: String? = nil
    
    @objc func initialize(_ call: CAPPluginCall) {
        if (call.hasOption("network")) {
            self.networkEnabled = call.getBool("network") ?? false
        }
        
        if (call.hasOption("crash_report")) {
            self.crashReportEnabled = call.getBool("crash_report") ?? false
        }
        
        if (call.hasOption("layout_inspector")) {
            self.layoutInspectorEnabled = call.getBool("layout_inspector") ?? false
        }
        
        if (call.hasOption("enabled")) {
            self.isEnabled = call.getBool("enabled") ?? false
        }
        
        if (call.hasOption("database")) {
            self.isEnabled = call.getBool("database") ?? false
            
            if (call.hasOption("database_path")) {
                self.databasePath = call.getString("database_path") ?? nil
            }
        }
        
        if (self.isEnabled) {
            let client = FlipperClient.shared()
            
            if (self.layoutInspectorEnabled) {
                let layoutDescriptorMapper = SKDescriptorMapper(defaults: ())
                FlipperKitLayoutComponentKitSupport.setUpWith(layoutDescriptorMapper)
                client?.add(FlipperKitLayoutPlugin(rootNode: application, with: layoutDescriptorMapper!))
            }
    
            if (self.networkEnabled) {
                client?.add(FlipperKitNetworkPlugin(networkAdapter: SKIOSNetworkAdapter()))
            }
            
            if (self.crashReportEnabled) {
                // TODO: Not Available
            }
            
            if (self.databaseEnabled) {
                // TODO: Not Available
            }
            
            client?.start()
        }
    }
    
    @objc func emulateCrash(_ call: CAPPluginCall) {
        // TODO: Not Available
    }
}
