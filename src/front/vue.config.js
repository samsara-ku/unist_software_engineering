const path = require("path");

module.exports = {
  outputDir: path.resolve(__dirname, "../" + "main/resources/public"),
  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:9000/",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
