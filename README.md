# react-native-jq-file-select
# install
npm install react-native-jq-file-select@1.2.1  --save
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
        import  FileManager from 'react-native-jq-file-select'
        
        ...
        FileManager.selectFile((response)=>{
             this.setState({
                //如果有错误，response.error不为空，
                //如果response.error，说明取到值了，文件路径存在response.path中
               aa:response.error+'---'+response.path,
            });
        });

