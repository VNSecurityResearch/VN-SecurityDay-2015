find $1 -name "*.apk" -exec echo "APK: {}" \; -exec sh -c 'aapt d permissions "{}"' \;
