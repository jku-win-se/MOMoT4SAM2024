import seaborn as sns
import pandas as pd
from matplotlib import pyplot as plt
from matplotlib.ticker import FuncFormatter
import os
import numpy as np

# Assuming your data is in a CSV file named 'your_data.csv'
# Replace 'your_data.csv' with the actual file path or URL
data = pd.read_csv(os.path.join('scripts', 'TXT.csv'), sep=" ", header=0)

data = data.sort_values(by='Cost', ignore_index=True)
# Extract columns a, b, c, d
a_values = data['Cost']
b_values = data['TotalBatteryLosses']
c_values = data['SoC']
d_values = data['TotalTractiveEnergy']


def add_axes(index, vals, label, col, marker, xytext, pos=None):
    ax2 = ax.twinx()
    if pos is not None:
        ax2.spines['right'].set_position(('outward', pos))
    scatter_b = ax2.scatter(index, vals, label=label, color=col, marker=marker)
    for i, txt in enumerate(vals):
        ax2.annotate( '{:,.2f}'.format(txt), (index[i], vals[i]), textcoords="offset points", xytext=xytext, ha='center', color=col)
    return ax2

def format_x(tick_val, tick_pos):
    if int(tick_val) in xs:
        return labels[int(tick_val)]
    else:
        return ''
# Create a figure and axis
fig, ax = plt.subplots(figsize=(data.shape[0], 8))


# Scatter plot for column 'a'
#scatter_a = ax.scatter(data.index, a_values, label='TotalTractiveEnergy', color='red', marker='x')
#for i, txt in enumerate(a_values):
#    ax.annotate( '{:,.2f}'.format(txt), (data.index[i], a_values[i]), textcoords="offset points", xytext=(20, -4), ha='center', color='red')
line_a = ax.plot(data.index, a_values, label='Cost', color='purple', marker='x', linestyle='-', linewidth=2)
for i, txt in enumerate(a_values):
    ax.annotate( '{:,.2}'.format(txt), (data.index[i], a_values[i]), textcoords="offset points", xytext=(30, -10), ha='center', color='purple')
    
# Create a twin axis for 'b'
ax2 = ax.twinx()
scatter_b = ax2.scatter(data.index, b_values, label='TotalBatteryLosses', color='blue', marker='x')
for i, txt in enumerate(b_values):
    ax2.annotate( '{:,.2f}'.format(txt), (data.index[i], b_values[i]), textcoords="offset points", xytext=(16, 5), ha='center', color='blue')
    
# Create a twin axis for 'c'
ax3 = ax.twinx()
ax3.spines['right'].set_position(('outward', 60))
scatter_c = ax3.scatter(data.index, c_values, label='SoC', color='green', marker='x')
for i, txt in enumerate(c_values):
    ax3.annotate( '{:,.2f}'.format(txt), (data.index[i], c_values[i]), textcoords="offset points", xytext=(-20, 0), ha='center', color='green')
    
# Create a twin axis for 'd'
ax4 = ax.twinx()
ax4.spines['right'].set_position(('outward', 120))
scatter_d = ax4.scatter(data.index, d_values, label='TotalTractiveEnergy', color='red', marker='x')
for i, txt in enumerate(d_values):
    ax4.annotate( '{:,.2f}'.format(txt), (data.index[i], d_values[i]), textcoords="offset points", xytext=(0, 5), ha='center', color='red')
    

# Set labels and title
ax.set_xlabel('')
ax.set_ylabel('Cost', color='purple')
ax2.set_ylabel('TotalBatteryLosses', color='blue')
ax3.set_ylabel('SoC', color='green')
ax4.set_ylabel('TotalTractiveEnergy', color='red')

# Set y-axis color
ax4.yaxis.label.set_color(scatter_d.get_facecolor()[0])
ax2.yaxis.label.set_color(scatter_b.get_facecolor()[0])
ax3.yaxis.label.set_color(scatter_c.get_facecolor()[0])
ax.yaxis.label.set_color(line_a[0].get_color())

# Move legend outside and below the plot
ax.legend(loc='upper left', bbox_to_anchor=(0.5, -0.2), frameon=False)
ax2.legend(loc='upper right', bbox_to_anchor=(0.5, -0.2), frameon=False)
ax3.legend(loc='lower left', bbox_to_anchor=(0.5, -0.2), frameon=False)
ax4.legend(loc='lower right', bbox_to_anchor=(0.5, -0.2), frameon=False)


ax.spines['top'].set_visible(False)
ax.spines['right'].set_visible(False)
ax2.spines['top'].set_visible(False)
ax2.spines['right'].set_visible(False)
ax3.spines['top'].set_visible(False)
ax3.spines['right'].set_visible(False)
ax4.spines['top'].set_visible(False)
ax4.spines['right'].set_visible(False)

# Define a custom tick formatter for the y-axis
def format_thousands(x, pos):
    return '{:,.3f}'.format(x)


# Apply the custom formatter to all y-axes
ax.yaxis.set_major_formatter(FuncFormatter(format_thousands))
ax2.yaxis.set_major_formatter(FuncFormatter(format_thousands))
ax3.yaxis.set_major_formatter(FuncFormatter(format_thousands))
ax4.yaxis.set_major_formatter(FuncFormatter(format_thousands))
# Show the plot

plt.xticks(np.arange(0, data.shape[0], step=1), labels=[s.replace(',','\n') for s in data['xLabel'].tolist()])


# Add text annotation at the specified coordinate
y_axis_annotation = 'BV (V)\nIR (ohm)\nnMT (N/m)\nAux (W)\nMP (W)'
ax.text(-0.05, -0.07, y_axis_annotation, transform=ax.transAxes, va='center', ha='right', color='black')

plt.tight_layout()
plt.show()

plt.savefig('output_plot.png', bbox_inches='tight', dpi=300)  # 300 dpi is just an example; adjust as needed
