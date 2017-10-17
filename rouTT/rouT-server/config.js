(function () {
    "use strict";

    exports.apiKey = process.env.GOOGLE_PLACES_API_KEY || "AIzaSyA19pkGacEYctC_xCWTBsWU7-JGPRifzr8";
    exports.outputFormat = process.env.GOOGLE_PLACES_OUTPUT_FORMAT || "json";

})();