package data.dto;

/**
 * LinkDto is dto of network's link.
 *
 * @param weight the link's weight (length)
 * @param capacity the link's capacity
 * @param tailVertexIndex tail vertex of the link
 * @param headVertexIndex head vertex of the link
 * @param label label
 */
public record LinkDto(
        double weight, double capacity, int tailVertexIndex, int headVertexIndex, String label
) {}
