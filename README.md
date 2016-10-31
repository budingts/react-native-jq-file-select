# react-native-jq-file-select
# install
npm install react-native-jq-file-select@1.0.0  --save
#Android 配置
        //file: android/settings.gradle
        ...
        include ':react-native-jq-file-select'
        project(':react-native-jq-file-select').projectDir = new File(settingsDir, '../node_modules/react-native-jq-file-select/android')
       
       //file: android/app/build.gradle
        ...
        dependencies {
            ...
            compile project(':react-native-jq-file-select')
        }

        //file: android/app/src/main/java/com/<...>/MainApplication.java

        ...
        import  FileManager from 'react-native-jq-file-select' // <-- add this import

        public class MainApplication extends Application implements ReactApplication {
         @Override
         protected List<ReactPackage> getPackages() {
               return Arrays.<ReactPackage>asList(
                   new MainReactPackage(),
                    new FileSelectReactPackage() // <-- add this line
               );
         }
        ...
        }
# Usage （RN）js代码中使用
        FileManager.selectFile((response)=>{
             this.setState({
               aa:response.error+'---'+response.uri+"----"+response.path,
            });
        });

