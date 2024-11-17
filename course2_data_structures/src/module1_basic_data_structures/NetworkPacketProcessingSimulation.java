//package module1_basic_data_structures;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Problem Description
 * <p>
 * Task. You are given a series of incoming network packets, and your task is to simulate their processing.
 * Packets arrive in some order. For each packet number 𝑖, you know the time when it arrived 𝐴𝑖 and the
 * time it takes the processor to process it 𝑃_𝑖 (both in milliseconds). There is only one processor, and it
 * processes the incoming packets in the order of their arrival. If the processor started to process some
 * packet, it doesn’t interrupt or stop until it finishes the processing of this packet, and the processing of
 * packet 𝑖 takes exactly 𝑃_𝑖 milliseconds.
 * <p>
 * The computer processing the packets has a network buffer of fixed size 𝑆. When packets arrive,
 * they are stored in the buffer before being processed. However, if the buffer is full when a packet
 * arrives (there are 𝑆 packets which have arrived before this packet, and the computer hasn’t finished
 * processing any of them), it is dropped and won’t be processed at all. If several packets arrive at the
 * same time, they are first all stored in the buffer (some of them may be dropped because of that —
 * those which are described later in the input). The computer processes the packets in the order of
 * their arrival, and it starts processing the next available packet from the buffer as soon as it finishes
 * processing the previous one. If at some point the computer is not busy, and there are no packets in
 * the buffer, the computer just waits for the next packet to arrive. Note that a packet leaves the buffer
 * and frees the space in the buffer as soon as the computer finishes processing it.
 * <p>
 * Input Format. The first line of the input contains the size 𝑆 of the buffer and the number 𝑛 of incoming
 * network packets. Each of the next 𝑛 lines contains two numbers. 𝑖-th line contains the time of arrival
 * 𝐴_𝑖 and the processing time 𝑃_𝑖 (both in milliseconds) of the 𝑖-th packet. It is guaranteed that the
 * sequence of arrival times is non-decreasing (however, it can contain the exact same times of arrival in
 * milliseconds — in this case the packet which is earlier in the input is considered to have arrived earlier).
 * Constraints. All the numbers in the input are integers. 1 ≤ 𝑆 ≤ 10^5; 0 ≤ 𝑛 ≤ 10^5; 0 ≤ 𝐴_𝑖 ≤ 10^6;
 * 0 ≤ 𝑃_𝑖 ≤ 10^3; 𝐴_𝑖 ≤ 𝐴_𝑖+1 for 1 ≤ 𝑖 ≤ 𝑛 − 1.
 * <p>
 * Output Format. For each packet output either the moment of time (in milliseconds) when the processor
 * began processing it or −1 if the packet was dropped (output the answers for the packets in the same
 * order as the packets are given in the input)
 * <p>
 * Sample.
 * Input:
 * 1 2
 * 0 1
 * 0 1
 * Output:
 * 0
 * -1
 * Explanation:
 * The first packet arrived at time 0, the second packet also arrived at time 0, but was dropped, because
 * the network buffer has size 1, and it was full of the first packet already. The first packet started
 * processing at time 0, and the second packet was not processed at all.
 */
public class NetworkPacketProcessingSimulation {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (Request request : requests) {
            responses.add(buffer.Process(request));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (Response response : responses) {
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.startTime);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}


class Request {
    public Request(int arrival_time, int processTime) {
        this.arrivalTime = arrival_time;
        this.processTime = processTime;
    }

    public int arrivalTime;
    public int processTime;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.startTime = start_time;
    }

    public boolean dropped;
    public int startTime;
}

class Buffer {
    public Buffer(int size) {
        this.size = size;
        this.finishTime = new ArrayList<>();
    }

    /**
     * Processes an incoming network packet and determines its start time or if it is dropped.
     * <p>
     * This method simulates the handling of a network packet in a buffer with fixed capacity.
     * It removes packets from the buffer that have finished processing before the current packet's
     * arrival, checks if the buffer has space for the current packet, and either processes or drops it.
     *
     * @param request The incoming packet containing its arrival time and processing time.
     * @return A {@code Response} object:
     * - If the packet is processed, the response contains the start time of processing.
     * - If the packet is dropped, the response indicates it was dropped with a start time of -1.
     * <p>
     * Time Complexity:
     * - Removing processed packets: O(n) amortized over all packets (each packet is removed once).
     * - Checking buffer size and adding finish time: O(1).
     * - Overall complexity for processing a single packet: O(1) amortized.
     * - Overall complexity for processing all packets: O(n), where n is the total number of packets.
     * <p>
     * Space Complexity:
     * - Buffer storage for finish times: O(S), where S is the size of the buffer.
     */
    public Response Process(Request request) {
        // Remove all packets that have already been processed before the arrival of the current packet
        while (!finishTime.isEmpty() && finishTime.get(0) <= request.arrivalTime) {
            finishTime.remove(0);
        }

        // Check if the buffer is full
        if (finishTime.size() >= size) {
            // Drop the packet as there is no space in the buffer
            return new Response(true, -1);
        }

        // Determine the start time of processing
        int start_time = finishTime.isEmpty()
                ? request.arrivalTime // If the buffer is empty, start processing immediately
                : finishTime.get(finishTime.size() - 1);// Else, start after the last packet finishes

        // Add the finishing time of the current packet to the buffer
        finishTime.add(start_time + request.processTime);

        // Return the response indicating the start time
        return new Response(false, start_time);
    }


    private final int size; // Maximum size of the buffer
    private final ArrayList<Integer> finishTime; // Finish times of packets currently in the buffer
}
