
  Pod::Spec.new do |s|
    s.name = 'CapacitorCommunityFlipper'
    s.version = '0.0.8'
    s.summary = 'A native plugin for flipper'
    s.license = 'MIT'
    s.homepage = 'https://github.com/capacitor-community/flipper'
    s.author = 'Priyank Patel <priyank.patel@stackspace.ca>'
    s.source = { :git => 'https://github.com/capacitor-community/flipper', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.static_framework = true
    s.dependency 'Capacitor'
    s.dependency 'FlipperKit'
    s.dependency 'Flipper-DoubleConversion'
    s.dependency 'Flipper-Folly'
    s.dependency 'Flipper-Glog'
    s.dependency 'Flipper-PeerTalk'
    s.dependency 'CocoaLibEvent'
    s.dependency 'OpenSSL-Universal'
    s.dependency 'CocoaAsyncSocket'
    s.dependency 'ComponentKit'
    s.dependency 'FlipperKit/SKIOSNetworkPlugin'
  end
