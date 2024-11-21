import axios from 'axios';


export async function signUpUser(user) {
    console.log('signUpUser function called with:', user);

    try {
        const response = await axios.post('http://localhost:8080/api/users/signUp', user); 
        console.log(`Registration successful: ${response.data} (status: ${response.status})`); 
        alert("Registration successful");

        return response.data;
    } catch (error) {
        if (error.response && error.response.status === 409) {
            console.log('Username already exists');
            alert("userName already exists please sign up with a new name");
            throw new Error('Username already exists');
        } else {
            console.log('An error occurred:', error.message);
            throw error;
        }
    }
}


export async function SigninUser(user) {
    try {
        const response = await axios.post('http://localhost:8080/api/users/Log_in', user);
        return response.data;
    } catch (error) {
        if (error.response && error.response.status === 404) {
            throw new Error("404");
        } else if (error.response && error.response.status === 400) {
            throw new Error("The username or password is incorrect");
        } else {
            throw new Error("An unexpected error occurred");
        }
    }
}

export default { signUpUser, SigninUser };






