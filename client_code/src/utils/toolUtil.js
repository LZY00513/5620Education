import {
	ElMessage,
	ElMessageBox,
	ElNotification
} from 'element-plus'
import menu from './menu';
import CryptoJS from './cryptojs.js'
let KEY = '1234567890123456'
let IV = 'abcdefghijklmnop'
const toolUtil = {
	// Message notification
	message(msg, type, close = null) {
		ElMessage({
			message: msg,
			type: type,
			duration: 2500,
			onClose() {
				if (close) {
					close()
				}
			}
		})
	},
	// Right-side notification
	notify(title, msg, type = 'success', close = null) {
		ElNotification({
			title: title,
			message: msg,
			type: type,
			onClose() {
				if (close) {
					close()
				}
			}
		})
	},
	storageSet(key, value) {
		localStorage.setItem(key, value);
	},
	storageGet(key) {
		return localStorage.getItem(key) ? localStorage.getItem(key) : "";
	},
	storageGetObj(key) {
		return localStorage.getItem(key) ? JSON.parse(localStorage.getItem(key)) : null;
	},
	storageRemove(key) {
		localStorage.removeItem(key);
	},
	storageClear() {
		localStorage.removeItem('frontToken');
		localStorage.removeItem('frontRole');
		localStorage.removeItem('frontSessionTable');
		localStorage.removeItem('frontName');
		localStorage.removeItem('toPath');
	},
	// Email validation
	isEmail(s) {
		return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
	},
	// Mobile number validation
	isMobile(s) {
		return /^(\+?61|0)4\d{8}$/.test(s);
	},
	// Phone number validation
	isPhone(s) {
		return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(s)
	},
	// URL validation
	isURL(s) {
		return /^http[s]?:\/\/.*/.test(s)
	},
	// Match a number (can be decimal, positive only, optional)
	isNumber(s) {
		return /(^-?[+-]?([0-9]*\.?[0-9]+|[0-9]+\.?[0-9]*)([eE][+-]?[0-9]+)?$)|(^$)/.test(s);
	},
	// Match an integer (optional)
	isIntNumer(s) {
		return /(^-?\d+$)|(^$)/.test(s);
	},
	// ID card validation
	checkIdCard(idcard) {
		const regIdCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		return regIdCard.test(idcard);
	},
	// Form validation
	fromValidate: {
		intNumber: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isIntNumer(value)) {
				callback(new Error("Please enter an integer"));
			} else {
				callback();
			}
		},
		number: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isNumber(value)) {
				callback(new Error("Please enter a number"));
			} else {
				callback();
			}
		},
		mobile: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isMobile(value)) {
				callback(new Error("Please enter a valid mobile number"));
			} else {
				callback();
			}
		},
		phone: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isPhone(value)) {
				callback(new Error("Please enter a valid phone number"));
			} else {
				callback();
			}
		},
		email: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isEmail(value)) {
				callback(new Error("Please enter a valid email address"));
			} else {
				callback();
			}
		},
		idCard: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.checkIdCard(value)) {
				callback(new Error("Please enter a valid ID card number"));
			} else {
				callback();
			}
		},
		url: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (!toolUtil.isURL(value)) {
				callback(new Error("Please enter a valid URL"));
			} else {
				callback();
			}
		},
		richText: (rule, value, callback) => {
			if (!value) {
				callback();
			} else if (/[^(<p>|</p>|<br>|\s)]/.test(value)) {
				callback();
			} else {
				callback(new Error("Please enter text"));
			}
		}
	},
	// Check if there is frontend permission
	isFrontAuth(tableName, key) {
		let role = 'Admin';
		let menus = menu.list();
		if (menus && menus.length) {
			for (let i = 0; i < menus.length; i++) {
				if (menus[i].roleName === role) {
					if (menus[i].frontMenu && menus[i].frontMenu.length) {
						for (let j = 0; j < menus[i].frontMenu.length; j++) {
							if (menus[i].frontMenu[j].child && menus[i].frontMenu[j].child.length) {
								for (let k = 0; k < menus[i].frontMenu[j].child.length; k++) {
									if (tableName === menus[i].frontMenu[j].child[k].tableName) {
										let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
										return buttons.includes(key) || false;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	},
	// Check if role has frontend permission
	isAuth(tableName, key) {
		let role = toolUtil.storageGet("frontRole");
		if (!role) return false;
		let menus = menu.list();
		if (menus && menus.length) {
			for (let i = 0; i < menus.length; i++) {
				if (menus[i].roleName === role) {
					if (menus[i].frontMenu && menus[i].frontMenu.length) {
						for (let j = 0; j < menus[i].frontMenu.length; j++) {
							if (menus[i].frontMenu[j].child && menus[i].frontMenu[j].child.length) {
								for (let k = 0; k < menus[i].frontMenu[j].child.length; k++) {
									if (tableName === menus[i].frontMenu[j].child[k].tableName) {
										return menus[i].frontMenu[j].child[k].buttons.includes(key);
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	},
	// Check if there is backend permission
	isBackAuth(tableName, key) {
		let role = toolUtil.storageGet("frontRole") || 'Admin';
		let menus = menu.list();
		if (menus && menus.length) {
			for (let i = 0; i < menus.length; i++) {
				if (menus[i].roleName === role) {
					if (menus[i].backMenu && menus[i].backMenu.length) {
						for (let j = 0; j < menus[i].backMenu.length; j++) {
							if (menus[i].backMenu[j].child && menus[i].backMenu[j].child.length) {
								for (let k = 0; k < menus[i].backMenu[j].child.length; k++) {
									if (tableName === menus[i].backMenu[j].child[k].tableName) {
										let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
										return buttons.includes(key) || false;
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	},
	// Get current date and time (yyyy-MM-dd hh:mm:ss)
	getCurDateTime() {
		let currentTime = new Date(),
			year = currentTime.getFullYear(),
			month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
			day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
			hour = currentTime.getHours(),
			minute = currentTime.getMinutes(),
			second = currentTime.getSeconds();
		return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
	},
	// Get current date (yyyy-MM-dd)
	getCurDate() {
		let currentTime = new Date(),
			year = currentTime.getFullYear(),
			month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
			day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
		return `${year}-${month}-${day}`;
	},
	// DES Encryption
	encryptDes(message) {
		const keyHex = CryptoJS.enc.Utf8.parse(KEY);
		const encrypted = CryptoJS.DES.encrypt(message, keyHex, {
			mode: CryptoJS.mode.ECB,
			padding: CryptoJS.pad.Pkcs7
		});
		return encrypted.toString();
	},
	// DES Decryption
	decryptDes(ciphertext) {
		const keyHex = CryptoJS.enc.Utf8.parse(KEY);
		const decrypted = CryptoJS.DES.decrypt({
			ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
		}, keyHex, {
			mode: CryptoJS.mode.ECB,
			padding: CryptoJS.pad.Pkcs7
		});
		return decrypted.toString(CryptoJS.enc.Utf8);
	},
	// AES Encryption
	encryptAes(msg) {
		let cmode = CryptoJS.mode.CBC;
		let cpad = CryptoJS.pad.Pkcs7;
		let ciphertext = CryptoJS.AES.encrypt(msg, CryptoJS.enc.Utf8.parse(KEY), {
			mode: cmode,
			padding: cpad,
			iv: CryptoJS.enc.Utf8.parse(IV)
		}).toString();
		return ciphertext;
	},
	// AES Decryption
	decryptAes(msg) {
		let cmode = CryptoJS.mode.CBC;
		let cpad = CryptoJS.pad.Pkcs7;
		var bytes = CryptoJS.AES.decrypt(msg, CryptoJS.enc.Utf8.parse(KEY), {
			mode: cmode,
			padding: cpad,
			iv: CryptoJS.enc.Utf8.parse(IV)
		});
		var originText = bytes.toString(CryptoJS.enc.Utf8);
		return originText;
	}
}
export default toolUtil;
