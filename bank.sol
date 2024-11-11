// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {
    // Mapping to store the balance of each customer
    mapping(address => uint256) private balances;

    // Mapping to track whether an account has been created for an address
    mapping(address => bool) private accountsCreated;

    // Event to emit when an account is created
    event AccountCreated(address indexed customer);

    // Event to emit when money is deposited
    event Deposited(address indexed customer, uint256 amount);

    // Event to emit when money is withdrawn
    event Withdrawn(address indexed customer, uint256 amount);

    // Event to emit when money is transferred
    event Transferred(address indexed from, address indexed to, uint256 amount);

    // Function to create an account
    function createAccount() public {
        require(!accountsCreated[msg.sender], "Account already created");
        accountsCreated[msg.sender] = true;
        balances[msg.sender] = 0; // Initialize with 0 balance
        emit AccountCreated(msg.sender);
    }

    // Function to deposit money into the account
    function deposit() public payable {
        require(accountsCreated[msg.sender], "Account not created");
        require(msg.value > 0, "Deposit amount must be greater than zero");
        balances[msg.sender] += msg.value;
        emit Deposited(msg.sender, msg.value);
    }

    // Function to withdraw money from the account
    function withdraw(uint256 amount) public {
        require(accountsCreated[msg.sender], "Account not created");
        require(amount > 0, "Withdrawal amount must be greater than zero");
        require(balances[msg.sender] >= amount, "Insufficient balance");

        balances[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
        emit Withdrawn(msg.sender, amount);
    }

    // Function to check the balance of the account
    function balance() public view returns (uint256) {
        require(accountsCreated[msg.sender], "Account not created");
        return balances[msg.sender];
    }

    // Function to transfer money to another account
    function transfer(address to, uint256 amount) public {
        require(accountsCreated[msg.sender], "Account not created");
        require(to != address(0), "Cannot transfer to the zero address");
        require(amount > 0, "Transfer amount must be greater than zero");
        require(balances[msg.sender] >= amount, "Insufficient balance for transfer");

        balances[msg.sender] -= amount;
        balances[to] += amount;

        emit Transferred(msg.sender, to, amount);
    }
}
