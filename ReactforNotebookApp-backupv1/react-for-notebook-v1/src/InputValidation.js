import React, {useState} from "react";

function Validation(values) {
  // Regular expression to check for non-readable characters
  const nonReadablePattern = /[^a-zA-Z0-9]/;

  // Check if username is empty
  if (!values.username || values.username.trim() === "") {
    return { isValid: false, message: "Username cannot be empty." };
  }

  // Check if password is empty
  if (!values.password || values.password.trim() === "") {
    return { isValid: false, message: "Password cannot be empty." };
  }

  // Check for non-readable characters in username
  if (nonReadablePattern.test(values.username)) {
    return { isValid: false, message: "Username contains non-readable characters." };
  }

  // Check for non-readable characters in password
  if (nonReadablePattern.test(values.password)) {
    return { isValid: false, message: "Password contains non-readable characters." };
  }

  // All validations passed
  return { isValid: true, message: "Validation successful." };
}

export default Validation;

// function Validation(values)
// {
//         // Regular expression to check for non-readable characters
//         const nonReadablePattern = /[^a-zA-Z0-9]/;
      
//         // Check if username is empty
//         if (!(values.username) || (values.username).trim() === "") {
//           return { isValid: false, message: "Username cannot be empty." };
//         }
      
//         // Check if password is empty
//         if (!(values.password) || (values.password).trim() === "") {
//           return { isValid: false, message: "Password cannot be empty." };
//         }
      
//         // Check for non-readable characters in username
//         if (nonReadablePattern.test((values.username))) {
//           return { isValid: false, message: "Username contains non-readable characters." };
//         }
      
//         // Check for non-readable characters in password
//         if (nonReadablePattern.test((values.password))) {
//           return { isValid: false, message: "Password contains non-readable characters." };
//         }
      
//         // All validations passed
//         return { isValid: true, message: "Validation successful." };
// }
      
// export default Validation;