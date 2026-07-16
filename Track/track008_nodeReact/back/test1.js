const {
    createUser, findUserByEmail, findUserById, 
    verifyUser, getAllUsers, updateUserNickname,
    deleteUser, findUserByNickname
    } = require('./models/users');

async function runTests(){
    try{
        //1. 회원가입
        await createUser('z@z', 'z', 'zzz', '010-1111-1111', 1, '1.png');
        console.log('✅회원가입 성공');

        //2. 이메일조회
        const userByEmail = await findUserByEmail('z@z');
        console.log('✅이메일조회 결과: ');
    }catch(err){
        console.log('❌ 테스트중 오류 발생 ', err);
    }
}

runTests();