// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {
    // Mapping to store the balance of each customer
    mapping(address => uint256) private balances;

    // Event to emit when money is deposited
    event Deposited(address indexed customer, uint256 amount);

    // Event to emit when money is withdrawn
    event Withdrawn(address indexed customer, uint256 amount);

    // Event to emit when money is transferred
    event Transferred(address indexed from, address indexed to, uint256 amount);

    // Function to deposit money into the account
    function deposit() public payable {
        require(msg.value > 0, "Deposit amount must be greater than zero");
        balances[msg.sender] += msg.value;
        emit Deposited(msg.sender, msg.value);
    }

    // Function to withdraw money from the account
    function withdraw(uint256 amount) public {
        require(amount > 0, "Withdrawal amount must be greater than zero");
        require(balances[msg.sender] >= amount, "Insufficient balance");

        balances[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
        emit Withdrawn(msg.sender, amount);
    }

    // Function to check the balance of the account
    function balance() public view returns (uint256) {
        return balances[msg.sender];
    }

    // Function to transfer money to another account
    function transfer(address to, uint256 amount) public {
        require(to != address(0), "Cannot transfer to the zero address");
        require(amount > 0, "Transfer amount must be greater than zero");
        require(balances[msg.sender] >= amount, "Insufficient balance for transfer");

        balances[msg.sender] -= amount;
        balances[to] += amount;

        emit Transferred(msg.sender, to, amount);
    }
}
