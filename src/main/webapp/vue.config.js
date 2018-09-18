var path = require('path')
module.exports = {
    outputDir: path.resolve(__dirname, '../../resources/static'),
    devServer: {
        proxy: {
            '/code': {
                target: 'http://localhost:9090',//在线测试
                // ws: true,
                changeOrigin: true,
                secure: false
            },
            '/api': {
                target: 'http://localhost:9090',//在线测试
                // ws: true,
                changeOrigin: true,
                secure: false
            },
        }
    }

}