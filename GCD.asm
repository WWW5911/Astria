;GCD
.386
.model flat,stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword
INCLUDE Irvine32.inc

.data
str_number byte "Enter a 32 bit number: ", 0
str_answer byte "Greatest common divisor is: ", 0
temp DWORD 0

.code
;	call Crlf 分行
main PROC
L1:
	mov EDX, offset str_number
	call WriteString
	call ReadInt;讀讀讀取
	cmp eax, 0
	JE L2
	mov ebx, eax
	call WriteString
	call ReadInt
	call CalcGcd
	mov EDX, offset str_answer
	call WriteString
	call WriteDec
	call Crlf
	call Crlf
	JMP L1
L2:
	exit
main ENDP

CalcGcd PROC
	cmp eax, 0 ;先做abs
	JGE L1
	call ABS
L1:	
	cmp ebx, 0 ;同上 但要先存好原EAX
	JGE L3
	mov temp, eax 
	mov eax, ebx
	call ABS
	mov ebx, eax
	mov eax, temp
L3:;x=eax y=ebx 
	xor edx, edx
	div ebx
	mov eax, ebx
	mov ebx, edx
	cmp ebx, 0
	JLE L4
	JMP L3
L4:
	ret
CalcGcd ENDP

ABS PROC
	dec eax
	xor eax, 11111111111111111111111111111111b
	ret
ABS ENDP

END main
