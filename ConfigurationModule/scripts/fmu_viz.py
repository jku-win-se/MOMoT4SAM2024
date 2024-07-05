import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.patches as patches
import os

lab_conv = {
    "TotalTractiveEnergy": "TTE (Wh)",
    "TotalBatteryLosses": "TBL (W)",
    "Auxload": "Aux (W)",
    "BatteryVoltage" : "BV (V)",
    "InternalRes": "IR (ohm)",
    "MaxTorque": "MT (N/m)",
    "MaxPower": "MP (W)",
    "SoC": "SoC (%)"
}


def draw_fmu(inputs, outputs, cost, input_labels, output_labels, solution_idx):
    fig, ax = plt.subplots()

    # Calculate the number of inputs and outputs
    num_inputs = len(inputs)
    num_outputs = len(outputs)
    plt.rcParams.update({'font.size': 10.5})

    # Calculate the maximum number of ports
    max_ports = max(num_inputs, num_outputs)

    # Limit the height of the FMU rectangle to 1.5
    fmu_height = max_ports*.115

    # Draw the rectangle representing the FMU
    fmu_width = 1.3
    fmu = patches.Rectangle((1, 1), fmu_width, fmu_height, linewidth=1, edgecolor='black', facecolor='lightgray')
    ax.add_patch(fmu)

    # Calculate vertical positions for input and output ports
    input_y_positions =  [1+(fmu_height - i * fmu_height / (num_inputs + 1)) for i in range(1, num_inputs + 1)]
    output_y_positions = [1+(fmu_height - i * fmu_height / (num_outputs + 1)) for i in range(1, num_outputs + 1)]

    # Draw input ports and labels
    for label, value, y in zip(input_labels, inputs, input_y_positions):
        ax.plot([0.9, 1], [y, y], color='black', linewidth=1.2)  # Draw a black horizontal line for input port
        ax.text(0.8, y, f"{value:.2f}", verticalalignment='center', horizontalalignment='right')  # Label on top
        ax.text(1.1, y, lab_conv[label], verticalalignment='center', horizontalalignment='left')  # Value at current position

    # Draw output ports and labels
    for label, value, y in zip(output_labels, outputs, output_y_positions):
        ax.plot([2.3, 2.4], [y, y], color='black', linewidth=1.2)  # Draw a black horizontal line for output port
        ax.text(2.5, y, f"{value:.2f}", verticalalignment='center', horizontalalignment='left')  # Label on top
        ax.text(2.22, y, lab_conv[label], verticalalignment='center', horizontalalignment='right')  # Value at current position

    # Add cost below the FMU
    ax.text(1.6, .86, f"Cost: {cost:,.2f}", horizontalalignment='center')

    # Set the limits and hide the axes
    ax.set_xlim(0, 3.3)
    ax.set_ylim(0, fmu_height + 1.02)
    ax.axis('off')


    # Add title for the solution index
    plt.title(f"Configuration {solution_idx + 1}")
    
    plt.savefig(f"solution_{solution_idx + 1}.png", format="png", transparent=True, dpi=600)


    # Show the plot
    # plt.show()


# Read the CSV file
df = pd.read_csv(os.path.join('scripts', 'fmu_results.csv'), sep=" ")

# Assume the first 5 columns are inputs, the next 3 are outputs, and the last column is cost
input_labels = df.columns[:5]
output_labels = df.columns[5:8]

# Loop through each row and draw the FMU schematic
for idx, row in df.iterrows():
    inputs = row[:5]
    outputs = row[5:8]
    cost = row[8]
    draw_fmu(inputs, outputs, cost, input_labels, output_labels, idx)

