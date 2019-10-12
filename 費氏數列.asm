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
