
# Shamir Secret Decoder (Java)

This project solves a simplified version of **Shamir's Secret Sharing** algorithm using **Lagrange interpolation** in Java. It reads polynomial root data from JSON files and computes the **constant term (c)** of the original polynomial.

## ✅ Problem Summary

Given encoded points (x, y) in various bases, this program:
- Parses and decodes the roots from the provided JSON
- Converts all `y` values to base 10
- Uses **Lagrange Interpolation** to recover the constant term `c`

## 📁 Files

- `SecretFinder.java` — Main program that reads JSON and computes the secret.
- `testcase1.json` — First test case.
- `testcase2.json` — Second test case.

## 💡 How to Run

1. Compile the Java file:

```bash
javac -cp .:json-20210307.jar SecretFinder.java
