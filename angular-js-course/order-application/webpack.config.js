/* global module, require, __dirname */
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: ['./app/app.js'],
  output: {
    path: __dirname + '/dist/app/',
    filename: '[hash].bundle.js'
  },
  devtool: 'source-map',
  module: {
    rules: [
      {
        enforce: 'pre',
        test: /\.js$/,
        exclude: /node_modules/,
        use: [{
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env'
            ],
            plugins: [
              'angularjs-annotate',
              '@babel/plugin-transform-runtime'
            ]
          }
        }]
      },
      {
        test: /\.html/,
        use: 'html-loader'
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './app/index.html',
      filename: 'index.html',
      inject: 'head'
    })
  ],
  devServer: {
    host: '0.0.0.0',
    port: 3000,
    contentBase: 'dist/app/',
    publicPath: '/',
    quiet: false,
    noInfo: false,
    headers: {}
  }
};
