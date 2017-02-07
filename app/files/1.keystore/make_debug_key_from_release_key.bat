@rem ##########################################################################
@rem keytool -importkeystore -v
@rem         -srckeystore [릴리즈 키스토어 파일이름]
@rem         -srcstorepass [릴리즈 키스토어 비밀번호]
@rem         -srcalias [릴리즈 alias]
@rem         -srckeypass [릴리즈 비밀번호]
@rem         -destkeystore [디버그 키스토어 파일이름]
@rem         -deststorepass android
@rem         -destalias androiddebugkey
@rem         -destkeypass android
@rem ##########################################################################

keytool -importkeystore -v ^
-srckeystore ollehkt_release.keystore ^
-srcstorepass ollehktwin@ ^
-srcalias "kt corporation" ^
-srckeypass ollehktwin@ ^
-destkeystore ollehkt_debug.keystore ^
-deststorepass android ^
-destalias androiddebugkey ^
-destkeypass android