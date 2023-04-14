module.exports = {
	paths: function (paths, env) {

		root = paths.appPath
		paths.appBuild = `${root}/target/classes/public`
		paths.appPublic = `${root}/static`
		paths.appHtml = `${root}/static/index.html`
		paths.appIndexJs = `${root}/src/main/js/index.js`
		// paths.appPackageJson = `${root}/package.json`
		paths.appSrc = `${root}/src/main/js`
		// paths.appTsConfig = `${root}/tsconfig.json`
		// paths.appJsConfig = `${root}/jsconfig.json`
		// paths.yarnLockFile = `${root}/yarn.lock`
		paths.testsSetup = `${root}/src/test/js/setupTests.js`
		// paths.proxySetup = `${root}/src/main/js/setupProxy.js`
		// paths.appNodeModules = `${root}/node_modules`
		// paths.swSrc = `${root}/src/main/js/service-worker.js`
		// paths.publicUrlOrPath = '/'
		// paths.ownPath = `${root}/node_modules/react-scripts`
		// paths.ownNodeModules = `${root}/node_modules/react-scripts/node_modules`
		// paths.appTypeDeclarations = `${root}/src/react-app-env.d.ts`
		// paths.ownTypeDeclarations = `${root}/node_modules/react-scripts/lib/react-app.d.ts`
		return paths;
	},

	jest: function(config) {

    		// use this to check original config:
    		// console.log(config)

    		config.rootDir = '/Users/npiiroinen/Documents/Apps/Solita2023/CityBikeApp'
    		config.roots = [
    			'<rootDir>/src/main/js',
    			'<rootDir>/src/test/js'
    		]
    		config.setupFilesAfterEnv = [
    			'<rootDir>/src/test/js/setupTests.js'
    		]
    		// config.modulePaths = [ ]

    		return config;
    	}
}