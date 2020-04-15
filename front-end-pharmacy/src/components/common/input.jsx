import React, { Component } from 'react';

// const Input = ({ name, label, value, onChange, error, type }) => {
//     return (
//         <div className="form-group">
//             <label htmlFor={name}>{label}</label>
//             <input
//                 value={value}
//                 onChange={onChange}
//                 id={name}
//                 name={name}
//                 type={type}
//                 className="form-control"
//             />
//             {error && <div className="alert alert-danger">{error}</div>}
//         </div>
//     );
// }

const Input = ({ name, label, error, ...rest }) => {
    return (
        <div className="form-group">
            <label htmlFor={name}>{label}</label>
            <input
                {...rest}
                id={name}
                name={name}
                className="form-control"
            />
            {error && <div className="alert alert-danger">{error}</div>}
        </div>
    );
}

export default Input;