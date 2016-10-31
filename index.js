'use strict'

const { NativeModules } = require('react-native');
const fiilemanager = NativeModules.JQFileSelectManager;
var  JQFileSelectManager={ };
JQFileSelectManager.selectFile = function(callback){

	return fiilemanager.startSelectFile(callback)
}
module.exports = JQFileSelectManager;
