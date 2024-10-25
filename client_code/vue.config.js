// var webpack = require('webpack');
// vue2
const path = require('path')

function resolve(dir) {
	return path.join(__dirname, dir)
}

function publicPath() {
	if (process.env.NODE_ENV == 'production') {
		return "././";
	} else {
		return "/";
	}
}

// vue.config.js
module.exports = {
	// publicPath:"././",
	publicPath: publicPath(),
	// Internationalization configuration: when using other languages, the Chinese language pack is still included by default

	configureWebpack: {
		resolve: {
			fallback: { path: require.resolve("path-browserify") },
		},
	},
	lintOnSave: false,
	devServer: {
		host: "0.0.0.0", // Specify the host. The default is localhost, so we keep the default here.
		port: 8082, // Specify the port
		hot: true, // Enable hot reloading
		https: false, // Whether to enable HTTPS mode
		proxy: { // Request proxy server
			[process.env.VUE_APP_BASE_API]: { // With the api prefix
				target: process.env.VUE_APP_BASE_API_URL, // Proxy target address
				changeOrigin: true,
				secure: false,
				pathRewrite: { // After sending the request, replace /api with an empty value so it doesn’t affect the interface request
					['^' + process.env.VUE_APP_BASE_API]: process.env.VUE_APP_BASE_API_PREFIX
				}
			}
		}
	},
	chainWebpack(config) {
		config.module
			.rule('svg')
			.exclude.add(resolve('src/icons'))
			.end()
		config.module
			.rule('icons')
			.test(/\.svg$/)
			.include.add(resolve('src/icons'))
			.end()
			.use('svg-sprite-loader')
			.loader('svg-sprite-loader')
			.options({
				symbolId: 'icon-[name]'
			})
			.end()
	}
}
