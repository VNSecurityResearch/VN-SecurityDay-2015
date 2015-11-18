find . -name "*.apk" -exec echo "APK: {}" \; -exec sh -c 'keytool -printcert -jarfile "{}" | grep MD5' \;
