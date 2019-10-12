;Fib
include Irvine32.inc
.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword
.data
max DWORD 7 ; 設定要求幾項 
array DWORD 100 DUP(1)
val DWORD 0

.code
main proc
	mov ecx, max
	mov edi, 0
	Fib:
		mov eax, max
		mov val, eax ; 現在跑的次數 
		sub val, ecx
		mov eax, val
		cmp eax, 0; 如果是第一就跳過
		je L3 ; 是的話
		jmp L1 ; 不是的話
	L1:
		cmp eax,1
		je L3
		jmp L2
	L2:
		mov eax, array[edi-4]
		add eax, array[edi-8]
		mov array[edi], eax
		jmp L4
	L3:
		mov eax, array[edi] 
	L4:
		add edi, 4
		call WriteDec
		call Crlf
	L5:
		loop Fib

	invoke ExitProcess,0
main endp
end main






;Fib
include Irvine32.inc
.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword
.data
val DWORD 0
val2 DWORD 1
val3 DWORD 1
val4 DWORD 1
max DWORD 7 ; 設定要求幾項
.code
main proc
	mov ecx, max

	Fib:
		mov eax, max
		mov val, eax ; 現在跑的次數 
		sub val, ecx
		mov eax, val
		cmp eax, 0; 如果是第一就跳過
		je L3 ; 是的話
		jmp L1 ; 不是的話
	L1:
		cmp eax,1
		je L3
		jmp L4

	L3:
		mov eax, 1
		call WriteDec
		call Crlf
		jmp L5
	L4:
		mov eax, val2
		add eax, val3
		call WriteDec
		mov val4, eax
		mov eax, val3
		mov val2, eax
		mov eax, val4
		mov val3, eax
		call Crlf
	L5:
		loop Fib

	invoke ExitProcess,0
main endp
end main
