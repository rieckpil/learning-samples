
## Navigation Timing API

```
// total page load time
var perfData = window.performance.timing; 
var pageLoadTime = perfData.loadEventEnd - perfData.navigationStart;
console.log(timeSincePageLoad)

// calculate request response time
var connectTime = perfData.responseEnd - perfData.requestStart;

// calculate page render time
var renderTime = perfData.domComplete - perfData.domLoading;

// Feature detects Navigation Timing API support.
if (window.performance) {
  // Gets the number of milliseconds since page load
  // (and rounds the result since the value must be an integer).
  var timeSincePageLoad = Math.round(performance.now());

  // Sends the timing event to Google Analytics.
  gtag('event', 'timing_complete', {
    'name': 'load',
    'value': timeSincePageLoad,
    'event_category': 'JS Dependencies'
  });
}
```