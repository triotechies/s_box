import numpy as np
import sympy as sp
class HillCipher:
    alpha = "abcdefghijklmnopqrstuvwxyz"
    def charEncoding(self, arr):
        s = ""
        for j in range(len(arr[0])):
            for i in range(len(arr)):
                s += HillCipher.alpha[arr[i][j]]
        return s
    def numEncoding(self, word, order):
        word += "x" * (order - len(word) % order) if len(word) % order != 0 else ""
        arr = np.zeros((order, len(word)//order))
        count = 0
        for j in range(len(word) // order):
            for i in range(order):
                arr[i][j] = HillCipher.alpha.index(word[count])
                count += 1
        return arr.astype(int)
    def scalar(self, arr, value):
        return (arr * value) % 26
    def modularInverse(self, a, b):
        t1 = 0
        t2 = 1
        t = 0
        while b != 0:
            q = a // b
            r = a % b
            a = b
            b = r
            t = t1 - (q * t2)
            t1 = t2
            t2 = t
        if a == 1:
            return t1 % 26
        else:
            return 0
    def det(self, arr):
        return int(np.linalg.det(arr)) % 26
    def adj(self, arr):
        arr = sp.Matrix(arr)
        adj = arr.adjugate()
        return np.array(adj).astype(int) % 26
    def multiply(self, arr_one, arr_two):
        return np.matmul(arr_one, arr_two).astype(int) % 26


obj = HillCipher()
# s = input("Enter a word: ")
# order = int(input("Enter order of matrix: "))
# key = np.zeros((order, order))
# for i in range(order):
#     for j in range(order):
#         key[i][j] = int(input("Enter element: "))
# # s = "hello"
order = 3
# print("******************** ENCRYPTION ********************")
# # key = np.array([[7, 6], [11, 17]])
# # key = np.array([[6, 24, 1], [13, 16, 10], [20, 17, 15]])
# int_matrix = obj.numEncoding(s, order)
# print("WORD CONVERTED TO MATRIX:")
# print(int_matrix)
# print("MATRIX AFTER MULTIPLCATION WITH KEY: ")
# cipher_matrix = obj.multiply(key, int_matrix)
# print(cipher_matrix)
# print("CIPHERED TEXT: ")
# cipher_text = obj.charEncoding(cipher_matrix)
# print(cipher_text)

# print("******************** DECRYPTION ********************")
# cipher_matrix = obj.numEncoding(cipher_text, order)
# deter = obj.det(key)
# print("DETERMINANT: ", deter)
# adj_mat = obj.adj(key)
# print("ADJOINT MATRIX: ", adj_mat)
# deter_inv = obj.modularInverse(26, deter)
# print("DETERMINANT INVERSE: ", deter_inv)
# k_inverse = obj.scalar(adj_mat, deter_inv)
# print("KEY INVERSE: ", k_inverse)
# plain_matrix = obj.multiply(k_inverse, cipher_matrix)
# print(plain_matrix)
# plain_text = obj.charEncoding(plain_matrix)
# print(plain_text) 

# k-1 = cp-1
print("known")
c = "ifomukrhj"
p = "vickyabet"
cipher_matrix = obj.numEncoding(c, order)
plain_matrix = obj.numEncoding(p, order)
det_p = obj.det(plain_matrix)
adj_p = obj.adj(plain_matrix)
det_inv = obj.modularInverse(26, det_p)
p_inverse = obj.scalar(adj_p, det_inv)
res = obj.multiply(cipher_matrix, p_inverse)
# result = obj.charEncoding(res)
# print(result)
det_k = obj.det(res)
adj_k = obj.adj(res)
inv_k = obj.modularInverse(26, det_k)
kres = obj.scalar(adj_k, inv_k)
print(kres)





