//1. imoprt, require [    ]
// useSelector  - 전역상태     // useDispatch  - 스토어알림
// useState     - 변수         // useEffect    - 이벤트변경감지
// useRouter    - 경로
// String targetUrl = redirectUri + "?access_token=" + access;     //쿼리스트링
import { useEffect, useRef } from "react";
import { useRouter } from "next/router";
import { useDispatch } from "react-redux";
import { loginSuccess } from "../../reducers/authReducer";
import { setAccessToken } from "../../reducers/authReducer";
import axios from "axios";



//2. 부품 + export
export default OAuth2Callbackpage = () => {
    const router = useRouter();         // 경로  
    const dispatch = useDispatch();     // 스토어 알림

    useEffect(() => {
        if (! router.isReady)return;
        const {accessToken} = router.query;  //쿼리스트링
        if(accessToken){
            try{
                localSteorage.setItem("accessToken", accessToken);  //로컬스토리지에 저장
                fatch(accessToken);  //스토어에 알림
            }catch(e){
                console.error("OAuth2Callbackpage error", err);
                router.push("/login");  //로그인페이지로 이동
            }
        }
    }, [router.query, router.isReady]);
    
    const fetchUser = async (accessToken) => {
        try{
            const res = await axios.get("http://localhost:8080/user/me", {
                headers: { Authorization: `Bearer ${accessToken}` },
            });
            const user = res.data;
            disspatch(loginSuccess({user, accessToken}));  //로그인 성공.
            router.push("/mypage");  //메인페이지로 이동
        }catch(err){
            console.error( "User fetch error", err);
            router.push("/login");  //로그인페이지로 이동
        }          
    };


    return (<p>소셜 로그인 처리 중입니다.</p>);
}
