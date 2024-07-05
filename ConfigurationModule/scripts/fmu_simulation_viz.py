import os
import pandas as pd
import matplotlib.pyplot as plt

lab_conv = {
    "TotalTractiveEnergy": "TTE",
    "BatteryLosses_W_": "TBL",
    "SoC": "SoC"
}

lab_sign = {
     "TotalTractiveEnergy": "Wh",
    "BatteryLosses_W_": "W",
    "SoC": "%"
}

# Directory containing the CSV files
directory = os.path.join('output', 'simulations')

# List all CSV files in the directory
csv_files = [file for file in os.listdir(directory) if file.endswith('.csv')]
for file_id, csv_file in enumerate(csv_files):
    
    df = pd.read_csv(os.path.join(directory, csv_file))

    # Assuming the columns are named col1, col2, and col3
    x_values = df.iloc[:, 0]  # Assuming the first column contains x-axis values
    data_columns = df.columns[1:]  # Assuming the rest of the columns contain data

    # Create subplots for each column
    fig, axes = plt.subplots(nrows=1, ncols=len(data_columns), figsize=(11.7, 8.3/3))

    # Plot each column
    for i, col in enumerate(data_columns):
        ax = axes[i]
        ax.plot(x_values, df[col], color="gray")
        ax.set_title(lab_conv[col])
        ax.set_xlabel('Time (s)')
        ax.set_ylabel(lab_sign[col])

    plt.tight_layout()
    fig.suptitle(f'Configuration {file_id+1}', y=1.03)
    plt.savefig(f"output/visuals/simulations_configuration_{file_id + 1}.png", format="png", bbox_inches='tight', transparent=True, dpi=600)

