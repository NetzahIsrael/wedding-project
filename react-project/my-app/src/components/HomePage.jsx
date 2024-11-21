import React from 'react';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';

function HomePage() {
    const user = useSelector((state) => state.user.currenUser);

    return (
        <div>
            <header>
                <Link to="/sign-up">
                    <button>Sign Up</button>
                </Link>
                <Link to ="/Login">
                <button>Sign In</button>
                </Link>

            </header>
            {user?.name ? (
                <h1>Hello, {user.name}!</h1>
            ) : (
                <h1>Welcome! Please sign up or log in.</h1>
            )}
        </div>
    );
}

export default HomePage;
