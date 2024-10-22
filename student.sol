// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentRegistry {
    // Structure to hold student information
    struct Student {
        string name;
        uint256 age;
        string major;
    }

    // Dynamic array to store students
    Student[] private students;

    // Event to emit when a student is added
    event StudentAdded(string name, uint256 age, string major);

    // Function to add a new student
    function addStudent(string memory _name, uint256 _age, string memory _major) public {
        Student memory newStudent = Student({
            name: _name,
            age: _age,
            major: _major
        });
        students.push(newStudent);
        emit StudentAdded(_name, _age, _major);
    }

    // Function to retrieve a student's data by index
    function getStudent(uint256 index) public view returns (string memory, uint256, string memory) {
        require(index < students.length, "Student does not exist");
        Student memory student = students[index];
        return (student.name, student.age, student.major);
    }

    // Function to get the total number of students
    function getTotalStudents() public view returns (uint256) {
        return students.length;
    }

    // Function to get all students
    function getAllStudents() public view returns (Student[] memory) {
        return students;
    }

    // Fallback function to handle unexpected Ether sent to the contract
    fallback() external payable {
        emit EtherReceived(msg.sender, msg.value);
    }

    // Receive function to handle plain Ether transfers
    receive() external payable {
        emit EtherReceived(msg.sender, msg.value);
    }

    // Event to log Ether received
    event EtherReceived(address indexed sender, uint256 amount);
}
