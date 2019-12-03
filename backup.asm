; Homework02 GCD find the greatest common divisor

INCLUDE Irvine32.inc

.data
info BYTE "Fibonacci Numbers by, Astria", 0
q1 BYTE "What is your name?: ", 0
hello BYTE "Hi, ", 0
q2 BYTE "How many Fibonacci numbers should I display?", 0
q21 BYTE "Enter an integer in the range [1..25]:", 0
q22 BYTE "That number was out of range, try again.", 0
endd BYTE "Goodbye, ", 0
F1 BYTE "F(", 0
F2 BYTE ") = F(", 0
F3 BYTE ") + F(", 0
F4 BYTE ")", 0
F5 BYTE ") = ", 0
Finti1 BYTE "F(1) = 1", 0
Finti0 BYTE "F(0) = 0", 0
namee BYTE 16 dup(?)
count DWORD 0
.code
main PROC
	mov EDX, offset info
	call WriteString
	call Crlf
	mov EDX, offset q1
	call WriteString
	mov edx,OFFSET namee	;讓寫入到namee eax會存字串長度
	mov ecx,SIZEOF namee ; 控制能輸入的最長長度
	call ReadString;讀讀讀取
	call Crlf
	mov EDX, offset hello
	call WriteString
	mov EDX, offset namee
	call WriteString
	call Crlf
	call Crlf
	jmp L2
L1:
	call Crlf
	mov EDX, offset q22
	call WriteString
	call Crlf
L2:
	mov EDX, offset q2
	call WriteString
	call Crlf
	mov EDX, offset q21
	call WriteString
	call ReadInt
	call Crlf
	cmp eax, 25
	JG L1
	cmp eax, 0
	JLE L1

	MOV count, EAX
	MOV ECX, 1
	call fib

main ENDP

fib proc
	push EBP
	mov EBP, ESP ; STACK FRAMEEEE
	sub ESP, 4 ;建立空間
	;印算式
	mov EDX, offset F1
	call WriteString
	MOV EAX, count
	add EAX, 2
	inc ecx
	sub EAX, ECX
	call WriteDec
	mov EDX, offset F2
	call WriteString
	dec EAX
	call WriteDec
	mov EDX, offset F3
	call WriteString
	dec EAX
	call WriteDec
	mov EDX, offset F4
	call WriteString
	call Crlf
	;印到這

	cmp ecx, count
	JE L3
	call fib
	push [EBP-16]
	;跑跑跑跑完了 開始往回 印
	mov EDX, offset F1
	call WriteString
	mov EAX, ECX
	call WriteDec
	mov EDX, offset F5
	call WriteString
	;印到這
	pop EAX 
	call WriteDec
	JMP L4
L3:
	mov EDX, offset Finti1
	call WriteString
	call Crlf
	mov EDX, offset Finti0
	call WriteString
	call Crlf
	mov DWORD PTR [EBP-4], 10
	mov ECX, 2
L4:
	mov esp, ebp
	pop ebp
	ret
fib ENDP

END main
