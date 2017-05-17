## Notes

Following out of scope due to time:

Espresso tests would require network interceptor with fake data to avoid flakiness (or idling resources, but no network dependency is better)

Proper variable caching would be to further invert dependencies, passing an okHttpFactory to the RetrofitFactory
and add extra layers for better composition and configurability.

Correct way of passing currency type from JSON would be to add business model layer.

Player api returns non-standard format, would require custom parsing to extract actual date before localising.
