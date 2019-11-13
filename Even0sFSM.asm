; 0s even or odd
.386
.model flat,stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword
INCLUDE Irvine32.inc

.data
str_number byte "Enter a binary sequence within 32bits: ", 0
str_true byte "True. This has even 0s", 0
str_false byte "False. This has odd 0s", 0
string_1 BYTE 32 DUP(0) ;緩衝區

.code
;    call Crlf 分行
main PROC;input sequence
L1:
    mov EDX, offset str_number
    xor ebx, ebx ;存目前的輸出
	
    call WriteString
	mov edx,OFFSET string_1	;讓寫入到string eax會存字串長度
	mov ecx,SIZEOF string_1 ; 控制能輸入的最長長度
    call ReadString;讀讀讀取
	xor ecx, ecx ;清空ECX
    cmp eax, 0
    JE L2
    call State_1 ;進入FSM
    cmp ebx, 0 
    JE FA
    mov EDX, offset str_true
    call WriteString
    call Crlf
	call Crlf
    JMP L1
FA:
    mov EDX, offset str_false
    call WriteString
    call Crlf
	call Crlf
    JMP L1
L2:
    exit
main ENDP

State_1 PROC; 這個STATE無論如何都為0
    cmp ecx, eax;如果序列到尾巴了 就跳出
    JE L3
    cmp byte ptr[edx+ecx], 30h ;查看是不是0
    JE L2
	inc ecx ;索引值++
    call State_1
    JMP L3
L2:
	inc ecx
    call State_2
L3:
    ret
State_1 ENDP

State_2 PROC; 現在有奇數個0
    MOV ebx, 0
    cmp ecx, eax;如果序列到尾巴了 就跳出
    JE L3
    cmp byte ptr[edx+ecx], 30h
    JE L2
	inc ecx
    call State_2
    JMP L3
L2:
	inc ecx
    call State_3
L3:
    ret
State_2 ENDP

State_3 PROC; 現在有偶數個0
    MOV ebx, 1
    cmp ecx, eax;如果序列到尾巴了 就跳出
    JE L3
    cmp byte ptr[edx+ecx], 30h
    JE L2
	inc ecx
    call State_3
	JMP L3
L2:
	inc ecx
    call State_2
L3:
    ret
State_3 ENDP

END main
